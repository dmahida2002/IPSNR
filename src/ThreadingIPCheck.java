
public class ThreadingIPCheck extends Thread {
	
	private String demandedIP;
	
	private String websiteList[] = 
		{				
				"https://whatismyipaddress.com/ip/",
				"https://nordvpn.com/ip-lookup/",
				"https://www.ipqualityscore.com/free-ip-lookup-proxy-vpn-test/lookup/"
		};
	
	private int currentWebsite;
	
	public ThreadingIPCheck(String demandedIP) {
		
		this.demandedIP = demandedIP;
	}
	
	@Override
	public void run() {
		
//		if (currentWebsite == 0) webOne();
		
		if (currentWebsite == 1) webTwo();
		
		if (currentWebsite == 2) webThree();
	}
	
	public int numOfChecks() {
		
		return websiteList.length;
	}
	
	public void setChecker(int i) {
		
		currentWebsite = i;
	}
	
	private void webOne() {
		
		System.out.println("Running check at whatismyipaddress...");
		
		BehaviorsForWhatIP performWhatIP = new BehaviorsForWhatIP();
		
		performWhatIP.setup(websiteList[currentWebsite], demandedIP);
		performWhatIP.getDetails();
		
		DetailedCompression.toReceiver(performWhatIP.finish());
	}
	
	private void webTwo() {
		
		System.out.println("Running check at nordvpn...");
		
		BehaviorsForNordVPN performNordVPN = new BehaviorsForNordVPN();
		
		performNordVPN.setup(websiteList[currentWebsite]);
		performNordVPN.enterIP(demandedIP);
		performNordVPN.clickCheck();
		performNordVPN.clickCheck();
		performNordVPN.getDetails();
		
		DetailedCompression.toReceiver(performNordVPN.finish());
	}
	
	private void webThree() {
		
		System.out.println("Running check at ipqualityscore...");
		
		BehaviorsForIPQual performIPQual = new BehaviorsForIPQual();
		
		performIPQual.setup(websiteList[currentWebsite], demandedIP);
		performIPQual.getDetails();
		
		DetailedCompression.toReceiver(performIPQual.finish());
	}
}
