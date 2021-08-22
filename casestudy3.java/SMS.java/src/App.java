import java.sql.*; 
import java.util.*;
class studentpage{
    Scanner sc=new Scanner(System.in);
    public void Studentc()
    {
        System.out.println("Welcome Student");
        System.out.println("1.Register");
        System.out.println("2.View Courses");
        System.out.println("3.Apply for a course");
        int c=sc.nextInt();
        switch(c)
        {
            case 1:
                    Reg();
                    break;
            case 2:
                    crs();
                    break;
            case 3:
                    acrs();
                    break;
            default:
                    break;
        }
    }
    public void Reg()
    {
        System.out.println("Enter ROLLNO , NAME , DATEOFBIRTH in order");
        Integer r=sc.nextInt();
        String name=sc.next();
        String date=sc.next();
        //Integer cid=sc.nextInt();
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/studentdatabase","root","password-1");  
            
            String sql="INSERT INTO student"+" (ROLLNO,NAME,DATEOFBIRTH)"+" VALUES( ?, ?, ?)";
             
            PreparedStatement stmt=con.prepareStatement(sql);  
            
            stmt.setInt(1,r);
            stmt.setString(2,name);
            stmt.setString(3,date);
            //stmt.setInt(4,cid); 
            stmt.executeUpdate();
            
            con.close();  
            System.out.println(("Record added"));
            }catch(Exception e){ System.out.println(e);}  
            
    }
    public void crs()
    {
        System.out.println("Courses available are:");

        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/studentdatabase","root","password-1");  
            //here sonoo is database name, root is username and password  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select COURSENAME from course;");  
            int i=0;
            while(rs.next()) 
            {
            i++; 
            System.out.println(i+"-"+rs.getString(1));  
            }
            con.close();  
            }catch(Exception e){ System.out.println(e);}

    }
    public void acrs()
    {
        System.out.println("Please enter your name");
        String n=sc.next();
        crs();
        System.out.println("Please Select the courseid corresponding to Courses given below");
        Integer cid=sc.nextInt();
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/studentdatabase","root","password-1");  
            
            String sql="UPDATE student"+" SET COURSEID=?"+" where NAME=?";
             
            PreparedStatement stmt=con.prepareStatement(sql);  
            stmt.setInt(1,cid); 
            stmt.setString(2,n);
            stmt.executeUpdate();
            
            con.close();  
            System.out.println(("CourseId added successfully"));
            }catch(Exception e){ System.out.println(e);}  
    }
    public void Adminc()
    {
        System.out.println("Welcome Admin");
        System.out.println("1.Add a new course");
        System.out.println("2.View Courses");
        System.out.println("3.View Student");
        int d=sc.nextInt();
        switch(d)
        {
            case 1:
                ncrs();
                break;
            case 2:
                crs();
                break;
            case 3:
                stud();
                break;
            default:
                break;
        }

    }
    public void  ncrs()
    {
        System.out.println("Please provide the new course Id,course name,duration,fees in order");
        int d=sc.nextInt();
        String cou=sc.next();
        int dur=sc.nextInt();
        int fee=sc.nextInt();
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/studentdatabase","root","password-1");  
            
            String sql="INSERT INTO course"+" (COURSEID,COURSENAME,DURATION,FEES)"+" VALUES(?,?,?,?)";
             
            PreparedStatement stmt=con.prepareStatement(sql);  
            stmt.setInt(1,d); 
            stmt.setString(2,cou);
            stmt.setInt(3,dur);
            stmt.setInt(4,fee);
            stmt.executeUpdate();
            
            con.close();  
            System.out.println(("Course  added successfully"));
            }catch(Exception e){ System.out.println(e);}
    }
    public void stud()
    {
        System.out.println("Students Present");
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/studentdatabase","root","password-1");  
            //here sonoo is database name, root is username and password  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select ROLLNO,NAME from student;");  
            int i=0;
            while(rs.next()) 
            {
            i++; 
            System.out.println(rs.getString(1)+" "+rs.getString(2));  
            }
            con.close();  
            }catch(Exception e){ System.out.println(e);}

    }
} 
class App{  
public static void main(String args[]){  
    System.out.println("Welcome To SMS(Student Management System)");
    System.out.println("Tell us who you are:");
    System.out.println("1.Student");
    System.out.println("2.Admin");
    studentpage s=new studentpage();
    int ch=0;
    Scanner sc=new Scanner(System.in);
    ch=sc.nextInt();
    switch(ch)
    {
        case 1:
                s.Studentc();
                break;
        case 2:
                s.Adminc();
                break;
        default:
                break;
    }
}  
}  