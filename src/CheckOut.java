import java.util.HashMap;

interface CheckOut
{
    double addTotal(HashMap<String,String> list);
    double discountCheck(HashMap<String,String> list);
    double finalBill();
}