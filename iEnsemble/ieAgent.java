package iEnsemble;

import jade.core.Agent;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.util.leap.Iterator;


public class ieAgent extends Agent {

	public ieAgent() {
		// TODO Auto-generated constructor stub
	}	
		
		protected void setup() {
			
			super.setup();	
			
			System.out.println("Name: "+getAID().getLocalName());
			System.out.println("GUID: "+getAID().getName());
			System.out.println("Addres: ");
			Iterator it = getAID().getAllAddresses();
			while (it.hasNext()) 
			{
				System.out.println("- "+it.next());
			}		
			
		}
		
		protected void takeDown() {
			
			System.out.println(getAID().getLocalName()+" Good Bye!");
			
			super.takeDown();
		}	

}
