package iEnsemble;

import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class ieAgentClassifier extends ieAgent {

	public ieAgentClassifier() {
		// TODO Auto-generated constructor stub
	}
	
    protected void setup() {
		
		System.out.println("iEnsemble - Agent Classifier");
		super.setup();
		
		// Registrando Agente para prover Servi�o Supervisao de Fase
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("ieAgentClassifier");
		sd.setName(getLocalName());
		dfd.addServices(sd);
		try 
		{
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) 
		{
			fe.printStackTrace();
		}
    }
    
 // DesRegistrando o Servi�os
 	protected void takeDown() {
 		// Retirando servico de classificacao das paginas amarelas
 		try 
 		{
 			DFService.deregister(this);
 		}
 		catch (FIPAException fe) 
 		{
 			fe.printStackTrace();
 		} 		
 		
 	}

}
