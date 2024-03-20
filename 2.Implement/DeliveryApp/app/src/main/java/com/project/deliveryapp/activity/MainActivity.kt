package com.project.deliveryapp.activity


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.project.deliveryapp.R
import com.project.deliveryapp.databinding.ActivityMainBinding
import com.project.deliveryapp.dialog.BlockTabDialog
import com.project.deliveryapp.dialog.SimpleDialog
import com.project.deliveryapp.fragment.cart.CartFragment
import com.project.deliveryapp.fragment.my_page.MyPageFragment
import com.project.deliveryapp.fragment.home.ShoppingFragment
import com.project.deliveryapp.fragment.home.FindMarketFragment
import com.project.deliveryapp.view_model.MainViewModel
import java.util.Stack


class MainActivity : AppCompatActivity() {
    val TAG: String = "MAIN_ACTIVITY"
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var mCurrentTab : TabTag
    private var fragmentStack = HashMap<TabTag, Stack<Fragment>>()

    private var callBlockDialog: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]

        mCurrentTab = TabTag.TAB_FIND
        fragmentStack[TabTag.TAB_FIND] = Stack<Fragment>()
        fragmentStack[TabTag.TAB_CART] = Stack<Fragment>()
        fragmentStack[TabTag.TAB_MY_PAGE] = Stack<Fragment>()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bnMenu.setupWithNavController(navController)

        setStacks()
        setNavigationClick()
        binding.bnMenu.selectedItemId = R.id.navigation_home
    }

    private fun setStacks() {
        fragmentStack[TabTag.TAB_FIND]!!.push(FindMarketFragment())
        fragmentStack[TabTag.TAB_CART]!!.push(CartFragment())
        fragmentStack[TabTag.TAB_MY_PAGE]!!.push(MyPageFragment())
    }
    private fun setNavigationClick() {
        binding.bnMenu.setOnItemSelectedListener { item ->
            mCurrentTab = when(item.itemId) {
                R.id.navigation_home -> TabTag.TAB_FIND
                R.id.navigation_cart -> TabTag.TAB_CART
                R.id.navigation_my_page -> TabTag.TAB_MY_PAGE
                else -> return@setOnItemSelectedListener false
            }

            val curFragment = supportFragmentManager.fragments[0]
            val nextFragment = fragmentStack[mCurrentTab]!!.lastElement()


            //ShoppingFragment 에서 tab 클릭시 Dialog 출력 및 처리
            if((curFragment is ShoppingFragment) && callBlockDialog) {
                if(mCurrentTab == TabTag.TAB_FIND) {
                    Log.d("Block", "HomeButton is not supported")
                    return@setOnItemSelectedListener false
                }

                val dialog = BlockTabDialog().getDialog()

                dialog.setButtonClickListener(object: SimpleDialog.OnButtonClickListener{
                    override fun onConfirmButtonClicked() {
                        dialog.dismiss()
                        callBlockDialog = false
                        fragmentStack[TabTag.TAB_FIND]!!.pop()
                        when(mCurrentTab) {
                            TabTag.TAB_CART -> binding.bnMenu.selectedItemId = R.id.navigation_cart
                            TabTag.TAB_MY_PAGE -> binding.bnMenu.selectedItemId = R.id.navigation_my_page
                            else -> Exception("Tag is Wrong.").stackTrace
                        }
                    }
                    override fun onCancelButtonClicked() {
                        dialog.dismiss()
                        mCurrentTab = TabTag.TAB_FIND
                    }

                })

                dialog.show(supportFragmentManager, "TabPressedDialog")
                return@setOnItemSelectedListener false
            }

            callBlockDialog = true
            pushFragments(mCurrentTab, nextFragment!!, false)

            Log.d("BackStack number: ", supportFragmentManager.backStackEntryCount.toString())
            Log.d("Fragment Tab", "Tab_Find: ${fragmentStack[TabTag.TAB_FIND]!!.size}" +
                    ", Tab_Cart: ${fragmentStack[TabTag.TAB_CART]!!.size}" +
                    ", Tab_MyPage: ${fragmentStack[TabTag.TAB_MY_PAGE]!!.size}")
            true
        }
    }


    //Separate Back Stack for each tab in BottomNavigationView Android using Fragments
    fun pushFragments(tag: TabTag, fragment: Fragment, shouldAdd: Boolean) {
        if(shouldAdd) fragmentStack[tag]!!.push(fragment)

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.nav_host_fragment_container, fragment)
        ft.commit()

    }
    fun popFragments() {
        val fragment: Fragment = fragmentStack[mCurrentTab]!!.elementAt(fragmentStack[mCurrentTab]!!.size - 2)
        fragmentStack[mCurrentTab]!!.pop()

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.nav_host_fragment_container, fragment)
        ft.commit()
    }
    fun toFindTab() {
        binding.bnMenu.selectedItemId = R.id.navigation_home
    }
    fun toCartTab(tag: TabTag, doClear: Boolean) {
        if(doClear) {
            while(fragmentStack[tag]!!.size != 1) {
                fragmentStack[tag]!!.pop()
            }
        }
        binding.bnMenu.selectedItemId = R.id.navigation_cart
    }
    fun toMyPageTab() {
        binding.bnMenu.selectedItemId = R.id.navigation_my_page
    }
    private fun popAll() {
        fragmentStack.forEach {
            it.value.clear()
        }
        setStacks()
        toFindTab()
    }

    fun startPaymentActivity(cartId: Long) {
        val intent = Intent(this@MainActivity, PaymentActivity::class.java)
        intent.putExtra("cartId", cartId)
        this@MainActivity.startActivity(intent)

        popAll()
    }

}
