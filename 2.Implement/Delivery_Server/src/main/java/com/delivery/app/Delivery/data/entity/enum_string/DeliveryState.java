package com.delivery.app.Delivery.data.entity.enum_string;

public enum DeliveryState {
    OnReception {
        @Override
        public String toString() {
            return "OnReception";
        }
    },
    OnDelivery{
        @Override
        public String toString() {
            return "OnDelivery";
        }
    },
    Delivered{
        @Override
        public String toString() {
            return "Delivered";
        }
    }
}
