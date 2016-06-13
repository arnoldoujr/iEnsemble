package iEnsemble;

import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class ieAgentEnsemble extends ieAgent {

	public AID[] AgentesClassificadores;
	
    protected void setup() {
		
		System.out.println("iEnsemble - Agent Ensemble");
		super.setup();
		
		// Registrando Agente para prover Serviço Supervisao de Fase
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("ieAgentEnsemble");
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
  	
  	public void ProcuraAgentesClassificadores()
	{
		// Atualiza Lista de Agentes Orientadores
		DFAgentDescription templateO = new DFAgentDescription();
		ServiceDescription sdO = new ServiceDescription();
		sdO.setType("ieAgentClassifier"); 
		templateO.addServices(sdO);
		try 
		{
			DFAgentDescription[] valor = DFService.search(this, templateO);
			AgentesClassificadores = new AID[valor.length];
			
			for (int i = 0; i < valor.length; ++i) 
			{
				
				//System.out.println(" Agentes Classificadores: " +valor[i].getName()+" Localname: "+valor[i].getName().getName() + "posicao "+valor[i].getName().getName().indexOf("Google")); 
				
				if   (valor[i].getName().getName().indexOf("OpenWeather") > -1)
					AgentesClassificadores[0] = valor[i].getName();
				else if   (valor[i].getName().getName().indexOf("Apixu") > -1)
						  AgentesClassificadores[1] = valor[i].getName();
				else if   (valor[i].getName().getName().indexOf("Forecast") > -1)
					  AgentesClassificadores[2] = valor[i].getName();	
				else if   (valor[i].getName().getName().indexOf("Trainer") > -1)
					  AgentesClassificadores[3] = valor[i].getName();
				
					
				//System.out.println(" Agentes Classificadores: "+valor[i].getName());
			}
			
			for (int i = 0; i < valor.length; ++i) 
			{
				System.out.println(" Agentes Classificadores: "+AgentesClassificadores[i].getLocalName());
			}
			
			//System.out.println(" Encontrado(s) "+String.valueOf(valor.length)+" Classificadores(es) ...");
		}
		catch (FIPAException fe)
		{
			fe.printStackTrace();
		}		
	}

}
