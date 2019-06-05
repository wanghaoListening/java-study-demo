package com.haothink.designpattern.template.hibernate;

public class HibernateTemplate {

	private void executeWithNativeSession(HibernateCallback callback){
		Session s = null;  
		try{  
			s = getSession();  
			s.beginTransaction();  

			//执行接口的方法，这样那个类要是实现了这个接口就得实现这方法就相当把这方法插到了此处  
			callback.doInHibernate(s);  

			s.getTransaction().commit();  

		}catch(Exception e){  
			s.beginTransaction().rollback();  
		}finally{  

		}  
	}

	private Session getSession() {  
		return new Session();  
	}  
	
	public void save(final Object o){
		new HibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			
			@Override
			public void doInHibernate(Session s) {
				s.save(o);
				
			}
		});
	}
}
