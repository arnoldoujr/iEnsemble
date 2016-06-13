package iEnsemble;

import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class ieAgentMonitor extends ieAgent {
	
	public AID ieEnsembleAgenteLeader;	

	public ieAgentMonitor() {
		// TODO Auto-generated constructor stub
	}
	
    protected void setup() {
		
		System.out.println("iEnsemble - Agent Monitor");
		super.setup();
		
		// Registrando Agente para prover Serviço Monitoracao
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("ieAgentMonitor");
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
  	
  	public void AtualizaStatusAmbiente()
	{
		// Atualiza Lista de Agentes Orientadores
		DFAgentDescription templateO = new DFAgentDescription();
		ServiceDescription sdO = new ServiceDescription();
		sdO.setType("ieAgentLeader"); 
		templateO.addServices(sdO);
		try 
		{
			DFAgentDescription[] valor = DFService.search(this, templateO);
			if  (valor.length > 0) {
				ieEnsembleAgenteLeader = valor[0].getName();
			}		
			
			System.out.println(" Encontrado(s) "+String.valueOf(valor.length)+" Leader(s) ...");
		}
		catch (FIPAException fe)
		{
			fe.printStackTrace();
		}		
	}

}
