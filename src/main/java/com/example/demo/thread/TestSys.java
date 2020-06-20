package com.example.demo.thread;

/**
 * @description:
 * @author: hetao
 * @create: 2020-01-08 10:28
 **/
public class TestSys {

    public static void main(String[] args){
        String customerCodes = "6005957504,6011060805,6011060941,6011736361,6013288520,6013288544,6014589875,6014895672,6014980565,6017703814,6018563815,6018769873,6018955551,6018955806,6019886818,6020048453,6020215188,6020215676,6020215703,6020215790,6020612125,6020654424,6020654624,6020662982,6020707651,6020716426,6020838415,6020940296,6020940525,6020940972,6020962415,6021240689,6021360646,6021360669,6022018984";
        String balances = "16.56,107.2,214.24,174.48,106.32,17.52,40.88,101.68,103.76,38.88,305.52,273.28,77.36,171.68,275.44,108.88,40,36.16,20.08,260.24,492.96,251.84,20.16,297.12,7.2,53.36,336.48,47.52,125.44,90.48,469.36,54.56,15.288,274,313.44";
        String[] customerCodeArr = customerCodes.split(",");
        String[] balancesArr = balances.split(",");

//        for(int i=0;i<customerCodeArr.length;i++){
//                System.out.println("UPDATE ami_customer SET customer_balance = customer_balance - "+balancesArr[i]+" WHERE customer_code = '"+customerCodeArr[i]+"';");
//
//        }

        String billingGass = "20.7,134,267.8,218.1,132.9,21.9,51.1,127.1,129.7,48.6,381.9,341.6,96.7,214.6,344.3,136.1,50,45.2,25.1,325.3,616.2,314.8,25.2,371.4,9,66.7,420.6,59.4,156.8,113.1,586.7,68.2,19.11,342.5,391.8";
        String[] billingGasArr = billingGass.split(",");

        String pricingCodes = "C3NR210,C3NR204,C3NR210,C3NR206,C3NR211,C3NR210,C3NR208,C3NR209,C3NR208,C3NR211,C3NR211,C3NR211,C3NR209,C3NR210,C3NR209,C3NR209,C3NR208,C3NR210,C3NR207,C3NR207,C3NR211,C3NR211,C3NR211,C3NR211,C3NR210,C3NR211,C3NR208,C3NR210,C3NR211,C3NR211,C3NR211,C3NR210,C3NR209,C3NR211,C3NR211";
        String[] pricingCodeArr = pricingCodes.split(",");



        for(int i=0;i<customerCodeArr.length;i++){
            System.out.println("INSERT INTO ami_billing (bill_date,company_id,customer_id,customer_name,it_enabled,billing_gas,it_operator,post_date,pricing_code,pricing_id,remark,meter_value," +
                    "update_date,before_reading_id,customer_code,reading_id,gprsmeter_id) VALUES " +
                    "('2018-12-31 06:06:06',(SELECT company_id from ami_customer WHERE customer_code='"+customerCodeArr[i]+"')," +
                    "(SELECT cid from ami_customer WHERE customer_code='"+customerCodeArr[i]+"')," +
                    "(SELECT customer_name from ami_customer WHERE customer_code='"+customerCodeArr[i]+"')," +
                    "1," +
                    "'"+billingGasArr[i]+"'," +
                    "'SYS'," +
                    "'2018-12-31 06:06:06'," +
                    "'"+pricingCodeArr[i]+"'," +
                    "null," +
                    "'补扣201912月，2阶梯少扣金额'," +
                    "'"+balancesArr[i]+"'," +
                    "'2018-12-31 06:06:06'," +
                    "NULL," +
                    "'"+customerCodeArr[i]+"'," +
                    "NULL," +
                    "(SELECT gprs.mid FROM ami_gprs_meter gprs INNER JOIN ami_customer customer ON gprs.customer_id = customer.cid WHERE customer.customer_code='"+customerCodeArr[i]+"') );");

        }


    }

}
