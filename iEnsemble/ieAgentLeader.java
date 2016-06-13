package iEnsemble;

import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class ieAgentLeader extends ieAgent {
	
	public AID ieEnsembleAgenteEnsemble;

	public ieAgentLeader() {
		// TODO Auto-generated constructor stub
	}
	
    protected void setup() {
		
		System.out.println("iEnsemble - Agent Leader");
		super.setup();
		
		// Registrando Agente para prover Serviço Leader
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("ieAgentLeader"); 
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
    
 // DesRegistrando o Serviços
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
  	
  	public void ProcuraAgentesEnsemble()
	{
		if   (ieEnsembleAgenteEnsemble == null) 
		{
  		
  		// Atualiza Lista de Agentes Orientadores
		DFAgentDescription templateO = new DFAgentDescription();
		ServiceDescription sdO = new ServiceDescription();
		sdO.setType("ieAgentEnsemble"); 
		templateO.addServices(sdO);
		try 
		{
			DFAgentDescription[] valor = DFService.search(this, templateO);
			if  (valor.length > 0) // É para ter apenas um
			{   
				ieEnsembleAgenteEnsemble = valor[0].getName();
			}		
			
			System.out.println(" Encontrado(s) "+String.valueOf(valor.length)+" Ensemble(s) ...");
		}
		catch (FIPAException fe)
		{
			fe.printStackTrace();
		}	
		}	
	}

}
