package com.design.patterns.framework.proxy.pattern;

/*
 By using a proxy, you can delay loading the resource until you really need the data inside. Without the concept of proxies,
 an application could be slow, and appear non-responsive.  
 Proxy mainly use for impl Lazy-Loading (for instance: Hibernate framework use Proxy for Lazy-Loading).
*/
public class ProxyImage implements IImage{

	private String fileName;
	private RealImage realImage;
	
	public ProxyImage(String fileName){
		this.fileName = fileName;
		realImage = null;
	}
	
	@Override
	public void display() throws Exception {
		if(realImage == null){
			realImage = new RealImage(fileName);
		}
		realImage.display();
	}
     
}
