public class Manager {
	private String name;
	private String empl_id;
	private int salary;
	private String email_id;
	private String mobile_no;
	//private Date date_of_join;
	private Boolean is_sales_clerk;
	private String password;
	
	public Manager(String n,String e,String em,String mob,Boolean check,String pass)
	{
		name=n;
		empl_id=e;
		email_id=em;
		mobile_no=mob;
		//date_of_join=dob;
		is_sales_clerk=check;
		salary=0;
		password=pass;
	}
	
	public String getname()
	{
		return name;
	}
	
	public String getempl_id()
	{
		return empl_id;
	}
	
	public String getmob()
	{
		return mobile_no;
	}
	
	
	
	
	
	
	
	public Boolean match_empl(String id,String pass)
	{
		return(id==email_id && password==pass);
	}
	
	
	public void change_mobile(String id,String oldpass,String mob)
	{
		if(match_empl(id,oldpass)==true) {
			mobile_no=mob;
		    }
		
	}
	
	
	
	public void change_email(String id,String oldpass,String email)
	{
		if(match_empl(id,oldpass)==true) {
			email_id=email;
		    }
		
	}
	
	public void change_password(String id,String oldpass,String pass)
	{
		if(match_empl(id,oldpass)==true) {
		password=pass;
	    }
	}
	
	
	public void query_statistics()
	{
		
	}
	
	public void query_inventory()
	{
		
	}
	
	public void update_prices()
	{
		
	}
	
	
}