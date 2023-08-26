import java.util.HashMap;

interface CheckOut
{
    double addTotal(HashMap<String,String> list);
    double discountCheck(HashMap<String,String> list);
    double finalBill();
    //this is the change added with vscode
}