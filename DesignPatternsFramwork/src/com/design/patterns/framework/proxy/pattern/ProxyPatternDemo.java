package com.design.patterns.framework.proxy.pattern;

 /*
  By using a proxy, you can delay loading the resource until you really need the data inside. Without the concept of proxies,
  an application could be slow, and appear non-responsive.  
  Proxy mainly use for impl Lazy-Loading (for instance: Hibernate framework use Proxy for Lazy-Loading).
 */
public class ProxyPatternDemo {
	
	public static void main(String[] args) throws Exception {

		//Use the ProxyImage to get object of RealImage class when required.
		 //the image not loading yet!
		 IImage image = new ProxyImage("test_10mb.jpg");
		
		 //image will be loaded from disk - (lazy loading)
	     image.display(); 
	     System.out.println("image will be loaded from disk");
	     
	    //image will not be loaded from disk
	    image.display(); 
	}
	
}
