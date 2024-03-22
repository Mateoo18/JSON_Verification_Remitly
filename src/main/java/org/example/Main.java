
package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
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