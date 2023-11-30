package net.alainpham.model;


public class Notification {

    private Long bookingId;
    private String customerName;
    private String channel;

    
    
    public Notification() {
    }

    public Notification(Long bookingId, String customerName, String channel) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.channel = channel;
    }
    
    @Override
    public String toString() {
        return "Notification [bookingId=" + bookingId + ", customerName=" + customerName + ", channel=" + channel + "]";
    }

    public Long getBookingId() {
        return bookingId;
    }
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }

    
}