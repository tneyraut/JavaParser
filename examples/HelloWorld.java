package test;

public class HelloWorld extends Azerty implements Qwerty,Qsdfg {
    
    /*ArrayList<String> array;
    int azerty;
    Animal a;*/
    
    static final int len = text.length();
    
    static public void main(String args[])
    {
        boolean inword = false;
        int nl = 0;
        int nw = 0;
        int nc = 0;
        
        for (int i=0;i<len;i++)
        {
            final char c = text.charAt(i);
            nc++;
            if(c == '\n')
                nl++;
            if(c == ' ' || c == '\n' || c == '\t')
                inword = false;
            else if(inword == false)
            {
                inword = true;
                nw = nw + 1;
            }
        }
        System.out.println(nl);
        System.out.println(nw);
        System.out.println(nc);
    }
    
    /*public void fib(int n)
    {
        int i = n-1, a = 1, b = 0, c = 0, d = 1, t;
        if (n <= 0)
        {
            return 0;
        }
        while (i > 0)
        {
            while (i % 2 == 0)
            {
                t = d * (2 * c + d);
                c = c * c + d * d;
                d = t;
                i = i / 2;
            }
            t = d * (b + a) + c * b;
            a = d * b + c * a;
            b = t;
            i--;
        }
        return a + b;
    }*/
    
    /*public void wxcvb(String[] args)  {
        try{
            System.out.println("=>"+(1/0));
        } catch(ClassCastException e){
            e.printStackTrace();
        }
        finally{
            System.out.println("action faite systematiquement");
        }
    }*/
    
    /*public void test()
    {
        switch (month) {
            case 1:  monthString = "January";
                break;
            case 2:  monthString = "February";
                break;
            case 3:  monthString = "March";
                break;
            case 4:  monthString = "April";
                break;
            case 5:  monthString = "May";
                break;
            case 6:  monthString = "June";
                break;
            case 7:  monthString = "July";
                break;
            case 8:  monthString = "August";
                break;
            case 9:  monthString = "September";
                break;
            case 10: monthString = "October";
                break;
            case 11: monthString = "November";
                break;
            case 12: monthString = "December";
                break;
            default: monthString = "Invalid month";
                break;
        }
    }*/
    
    /*public int methodWhileIfReturn() {
        while (1) {
            if (0) {
                return -1;
            }
        }
        return 0;
    }*/
    
    /*public int methodWhileIfBreak() {
        while (1) {
            if (0) {
                break;
            }
        }
        return 0;
    }*/

}
