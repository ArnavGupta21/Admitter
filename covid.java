import java.util.*;

public class Interface {
    static Scanner in=new Scanner(System.in);
    static class patient{
        String name;
        float temp;
        int oxlevel;
        int age;
        int id;
        String status;
        String institute;
        int recdays;

        patient(String n,float t,int ox,int age,int id){
            this.name=n;
            this.temp=t;
            this.oxlevel=ox;
            this.age=age;
            this.id=id;
            this.status="Not_Admitted";
            this.institute="Not_Assigned";
            this.recdays=-1;
        }
    }
//--------------------------------------------------------------------------------------
    static class institute{

        String name;
        float temp;
        int oxlevel;
        int beds;
        String status;

        institute(String name,float t,int o,int b){
            this.name=name;
            this.temp=t;
            this.oxlevel=o;
            this.beds=b;
            this.status="Open";
        }
    }
//--------------------------------------------------------------------------------------

    public static void fill(institute[] I,int index,patient[] p,int n){

        System.out.println(I[index].name);
        System.out.println("Temperature should be <= "+I[index].temp);
        System.out.println("Oxygen Levels should be >= "+I[index].oxlevel);
        System.out.println("Number of Available beds - "+I[index].beds);
        System.out.println("Admission Status - OPEN");
        for(int i=0;i<n;i++){
            if(p[i].status.equals("Not_Admitted") && I[index].beds>0 && p[i].oxlevel>=I[index].oxlevel){
                    p[i].status="Admitted";
                    p[i].institute=I[index].name;
                    System.out.println("Recovery days for admitted patient ID "+p[i].id+" - ");
                    p[i].recdays= in.nextInt();
                    I[index].beds=I[index].beds-1;
            }
        }

        for(int i=0;i<n;i++){
            if(p[i].status.equals("Not_Admitted") && I[index].beds>0 && p[i].temp<=I[index].temp){
                    p[i].status="Admitted";
                    p[i].institute=I[i].name;
                    System.out.println("Recovery days for admitted patient ID "+p[i].id+" - ");
                    p[i].recdays= in.nextInt();
                    I[index].beds--;
            }
        }

        if(I[index].beds==0){
            I[index].status="Closed";
        }
    }

//--------------------------------------------------------------------------------------
    public static void remove(patient[] p,int n){
        int[] arr=new int[n];
        int count=0;
        for(int i=0;i<n;i++){
            if(p[i].status.equals("Admitted")){
                arr[count]=p[i].id;
                count++;
                p[i].status="Removed";
            }
        }
        if(count>0){
            System.out.println("Account ID removed of admitted patients");
            for(int i=0;i<count;i++){
                System.out.println(arr[i]);
            }
        }
        else{
            System.out.println("No admitted patients");
        }
    }

//--------------------------------------------------------------------------------------
    public static void remove(institute[] I,int n){
        System.out.println("Accounts removed of Institute whose admission is closed");
        for(int i=0;i<n;i++){
            if(I[i].status.equals("Closed")){
                System.out.println(I[i].name);
                I[i].status="Removed";
            }
        }
    }
//--------------------------------------------------------------------------------------

    public static boolean check(patient[] p,int n){
        for(int i=0;i<n;i++){
            if(p[i].status.equals("Not_Admitted")){
                return false;
            }
        }
        return true;
    }
//--------------------------------------------------------------------------------------
// Query 9 function
    public static void print(String name,patient[] p,int n){
        int count=0;
        for(int i=0;i<n;i++){
                if (p[i].institute.equals(name) && p[i].status.equals("Admitted")) {
                    System.out.println(p[i].name + ", recovery time is " + p[i].recdays + " days");
                    count++;
                }

        }
        if(count==0){
            System.out.println("No Patient in this Institute");
        }
    }
//--------------------------------------------------------------------------------------
    public static void print(patient[] p,int n){
        for(int i=0;i<n;i++){
            if(!p[i].status.equals("Removed")) {
                System.out.println(p[i].id + " " + p[i].name);
            }
        }
    }
//--------------------------------------------------------------------------------------

    public static void print(patient[] p,int n,String criteria){
        int t=0;
        for(int i=0;i<n;i++){
            if(p[i].status.equals(criteria)){
                t=t+1;
            }
        }
        System.out.println(t+" patients");
    }
//--------------------------------------------------------------------------------------

    public static void print(int id,patient[] p){
        int index=id-1;
        System.out.println(p[index].name);
        System.out.println("Temperature is "+p[index].temp);
        System.out.println("Oxygen Level is "+p[index].oxlevel);
        System.out.println("Admission Status - "+p[index].status);
        if(p[index].status.equals("Admitted")){
            System.out.println("Admitting Institute - "+p[index].institute);
        }
    }
//--------------------------------------------------------------------------------------
    public static void print(String name,institute[] I,int n){
        for(int i=0;i<n;i++){
            if(I[i].name.equals(name)){
                System.out.println(I[i].name);
                System.out.println("Temperature should be <= "+I[i].temp);
                System.out.println("Oxygen Level should be >= "+I[i].oxlevel);
                System.out.println("Number of Available beds - "+I[i].beds);
                System.out.println("Admission Status - "+I[i].status);
                break;
            }
        }
    }
//--------------------------------------------------------------------------------------
    public static void print(institute[] I,int n){
        int t=0;
        for(int i=0;i<n;i++){
            if(I[i].status.equals("Open")){
                t=t+1;
            }
        }
        System.out.println(t+" institutes are admitting patients currently");
    }
//--------------------------------------------------------------------------------------


    public static void main(String[] args) {
        int n=in.nextInt();
        patient[] p=new patient[n];
        institute[] I=new institute[n];
        int institute_count=0;
        for(int i=0;i<n;i++){
            String name=in.next();
            float temp=in.nextFloat();
            int ox=in.nextInt();
            int a=in.nextInt();
            p[i]=new patient(name,temp,ox,a,i+1);
            I[i]=new institute("Not_Assigned",-1,-1,-1);
        }

        while(!check(p, n)){
            int q=in.nextInt();
            if(q==1){
                String name=in.next();
                float t=in.nextFloat();
                int o=in.nextInt();
                int b=in.nextInt();
                I[institute_count].name=name;  //new institute(name,t,o,b);
                I[institute_count].temp=t;
                I[institute_count].oxlevel=o;
                I[institute_count].beds=b;
                fill(I,institute_count,p,n);
                institute_count=institute_count+1;
            }
            else if(q==2){
                remove(p,n);
            }
            else if(q==3){
                remove(I,institute_count);
            }
            else if(q==4){
                print(p,n,"Not_Admitted");
            }
            else if(q==5){
                print(I,institute_count);
            }
            else if(q==6){
                String name=in.next();
                print(name,I,institute_count);
            }
            else if(q==7){
                int id=in.nextInt();
                if(!p[id - 1].status.equals("Removed")){
                    print(id,p);
                }
                else{
                    System.out.println("Account Not Found");
                }
            }
            else if(q==8){
                print(p,n);
            }
            else if(q==9){
                String name=in.next();
                print(name,p,n);
            }
        }
    }
}














/*
*
    /*public static boolean check(String name,institute[] I,int n) {
        for (int i = 0; i < n; i++) {
            if (I[i].name.equals(name)) {
                if (I[i].status.equals("Open")) {
                    return true;
                }
            }
        }
        return false;
    }*/
//--------------------------------------------------------------------------------------
//public static boolean check(int id,patient[] p){
  //  return !p[id - 1].status.equals("Removed");
//}
//--------------------------------------------------------------------------------------
//
