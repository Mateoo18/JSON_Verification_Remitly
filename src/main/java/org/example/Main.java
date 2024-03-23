
package org.example;

public class Main {
    public static void main(String[] args) {

        String path="src/main/resources/AWS_ROLE.json";
        Aws_Verify awsVerify = Aws_Verify.readAws_Verify(path);

        boolean[] ans;
        ans=awsVerify.verify();
        for(int i=0;i<ans.length;i++){
            if(ans[i]==true){

                System.out.println("Statement "+(i+1)+" correct.");
            }
            else{
                
                System.out.println("Statement "+(i+1)+" contains * - incorrect.");
            }
        }
    }
}