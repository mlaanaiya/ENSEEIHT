package simplepdl.manip;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import simplepdl.Process;
import petrinet.*;
import simplepdl.*;
 

public class SimplePDL2PetriNet {

	// La fabrique pour fabriquer les éléments de PetriNet
    static PetrinetFactory myFactory;
    static PetriNet mypetri;
    
    
    static Map<String, Place> Ready = new HashMap<String, Place>();
    static Map<String, Place> Started = new HashMap<String, Place>();
    static Map<String, Place> Running = new HashMap<String, Place>();
    static Map<String, Place> Finished = new HashMap<String, Place>();
    static Map<String, Transition> Start = new HashMap<String, Transition>();
    static Map<String, Transition> Finish = new HashMap<String, Transition>();
    
	public static void main(String[] args) {
		
		// Charger le package SimplePDL afin de l'enregistrer dans le registre d'Eclipse.
		SimplepdlPackage packageInstanceSimplePDL = SimplepdlPackage.eINSTANCE;
		// Charger le package PetriNet afin de l'enregistrer dans le registre d'Eclipse.
		PetrinetPackage packageInstancePetriNet = PetrinetPackage.eINSTANCE;
		
		// Enregistrer l'extension ".xmi" comme devant être ouverte à 
		// l'aide d'un objet "XMIResourceFactoryImpl"
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());
		
		// Créer un objet resourceSetImpl qui contiendra une ressource EMF (le modèle)
		ResourceSet resSet = new ResourceSetImpl();
		
		// Créer le modèle de sorte (PetriNet.xmi)
		URI SortieURI = URI.createURI("out.xmi");
		Resource Sortie = resSet.createResource(SortieURI);
		
		// Créer un objet resourceSetImpl qui contiendra une ressource EMF (le modèle)
		ResourceSet resSetModel = new ResourceSetImpl();
		// Charger la ressource (notre modèle)
		URI modelURI = URI.createURI("SimplePDL.xmi");
		Resource resource = resSetModel.getResource(modelURI, true);
		
		
		
		// Récupérer le premier élément du modèle (élément racine)
		Process process = (Process) resource.getContents().get(0);
		
		// Instancier la fabrique
	    myFactory = PetrinetFactory.eINSTANCE;
	    
	    // Créer le PetriNet
	    mypetri = myFactory.createPetriNet();
	    mypetri.setName(process.getName());
	    Sortie.getContents().add(mypetri);
	    
	    
	    for (Object o : process.getProcessElements()) {
	    	
	    	// Conversion WorkDefinition to Place
			if (o instanceof WorkDefinition) {
				WorkDef((WorkDefinition)o);
			}
			
	    	// Conversion WorkSequence to Arc
			else if (o instanceof WorkSequence) {
				WorkSeq((WorkSequence)o);
			}

	    	// Conversion Ressource to Place
			else if (o instanceof Ressource) {
				ConvertRessource((Ressource)o);
			}
		}
	    
	    // Sauvegarde de la ressource de sortie
	    try {
	    	Sortie.save(Collections.EMPTY_MAP);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	private static void ConvertRessource(Ressource ressource) {
		// Création de la Place Ressource
		Place PlaceRessource = myFactory.createPlace();

			// Paramétrage des Places
		// Nommer la ressource
		PlaceRessource.setName("Ressource_" + ressource.getName());
		// Initialisation du nombre de jeton
		PlaceRessource.setTokens(ressource.getQuantite());
		
		// Ajout de la Place à mypetri
		mypetri.getPetrinetelement().add(PlaceRessource);
		
		// Liaison Ressource-WorkDefinition
		for (Allocate d : ressource.getAllocate()) {
			// Ajout de l'arc entre la PlaceRessource et 
			// la transition start correspondante
			Edge arcDemande = myFactory.createEdge();
			arcDemande.setSource(PlaceRessource);
			System.out.println(d.getWorkdefinition());
			arcDemande.setCible(Start.get(d.getWorkdefinition().getName()));
			arcDemande.setTokens(d.getOccurences());
			mypetri.getPetrinetelement().add(arcDemande);
			
			// Ajout de l'arc entre la PlaceRessource et 
			// la transition finish correspondante
			Edge arcRetour = myFactory.createEdge();
			arcRetour.setSource(Finish.get(d.getWorkdefinition().getName()));
			arcRetour.setCible(PlaceRessource);
			arcRetour.setTokens(d.getOccurences());
			mypetri.getPetrinetelement().add(arcRetour);
		}
		
	}

	private static void WorkSeq(WorkSequence workSeq) {
		// Création de l'arc
		Edge myArc = myFactory.createEdge();
		
		switch (workSeq.getLinkType()) {
		case START_TO_START :
			myArc.setSource(Started.get(workSeq.getPredecessor().getName()));
			myArc.setCible(Start.get(workSeq.getSuccessor().getName()));
			break;
		case START_TO_FINISH :
			myArc.setSource(Started.get(workSeq.getPredecessor().getName()));
			myArc.setCible(Finish.get(workSeq.getSuccessor().getName()));
			break;
		case FINISH_TO_START :
			myArc.setSource(Finished.get(workSeq.getPredecessor().getName()));
			myArc.setCible(Start.get(workSeq.getSuccessor().getName()));
			break;
		case FINISH_TO_FINISH :
			myArc.setSource(Finished.get(workSeq.getPredecessor().getName()));
			myArc.setCible(Finish.get(workSeq.getSuccessor().getName()));
			break;
		default: break;
		
		}
		myArc.setType(arc_type.READ_ARC);
		myArc.setTokens(1);
		
		// Ajout de l'arc à myetri
		mypetri.getPetrinetelement().add(myArc);
		
	}

	private static void WorkDef(WorkDefinition workDef) {
		// Création des Places
		Place PlaceReady = myFactory.createPlace();
		Place PlaceStarted = myFactory.createPlace();
		Place PlaceFinished = myFactory.createPlace();
		Place PlaceRunning= myFactory.createPlace();
		
		// Création des Transitions
		Transition TransitionStart = myFactory.createTransition();
		Transition TransitionFinish = myFactory.createTransition();
		
		// Création des Arcs
		Edge Arc_Ready_Start = myFactory.createEdge();
		Edge Arc_Start_Started = myFactory.createEdge();	
		Edge Arc_Start_Running = myFactory.createEdge();
		Edge Arc_Running_Finish = myFactory.createEdge();
		Edge Arc_Finish_Finished = myFactory.createEdge();
		
			// Paramétrage des Places
		// Determination du nom des Places
		PlaceReady.setName(workDef.getName() + "_ready");
		PlaceStarted.setName(workDef.getName() + "_started");
		PlaceFinished.setName(workDef.getName() + "_finished");
		PlaceRunning.setName(workDef.getName() + "_running");
		
		
		// Initialisation du jeton des Places
		PlaceReady.setTokens(1);
		PlaceStarted.setTokens(0);
		PlaceRunning.setTokens(0);
		PlaceFinished.setTokens(0);

		
			// Paramétrage des Transitions
		// Determination du nom des Transitions
		TransitionStart.setName(workDef.getName() + "_start");
		TransitionFinish.setName(workDef.getName() + "_finish");

		
			// Paramétrage des Arcs
		// Determination de la source et la destination de chaque Arc
		Arc_Ready_Start.setSource(PlaceReady);
		Arc_Ready_Start.setCible(TransitionStart);
		
		Arc_Running_Finish.setSource(PlaceRunning);
		Arc_Running_Finish.setCible(TransitionFinish);
		
		Arc_Start_Started.setSource(TransitionStart);
		Arc_Start_Started.setCible(PlaceStarted);
		
		Arc_Start_Running.setSource(TransitionStart);
		Arc_Start_Running.setCible(PlaceRunning);
		
		Arc_Finish_Finished.setSource(TransitionFinish);
		Arc_Finish_Finished.setCible(PlaceFinished);
		
		// Determination du type de chaque Arc
		Arc_Ready_Start.setType(arc_type.NORMAL);
		Arc_Running_Finish.setType(arc_type.NORMAL);
		Arc_Start_Started.setType(arc_type.NORMAL);
		Arc_Start_Running.setType(arc_type.NORMAL);
		Arc_Finish_Finished.setType(arc_type.NORMAL);
		
		// Determination des jetons de chaque Arc
		Arc_Ready_Start.setTokens(1);
		Arc_Running_Finish.setTokens(1);
		Arc_Start_Started.setTokens(1);
		Arc_Start_Running.setTokens(1);
		Arc_Finish_Finished.setTokens(1);
	
		
		// Ajout des Places à mypetri
		mypetri.getPetrinetelement().add(PlaceReady);
		mypetri.getPetrinetelement().add(PlaceStarted);
		mypetri.getPetrinetelement().add(PlaceRunning);
		mypetri.getPetrinetelement().add(PlaceFinished);
		
		// Ajout des Transitions à mypetri
		mypetri.getPetrinetelement().add(TransitionStart);
		mypetri.getPetrinetelement().add(TransitionFinish);
		
		// Ajout des Arcs à mypetri
		mypetri.getPetrinetelement().add(Arc_Ready_Start);
		mypetri.getPetrinetelement().add(Arc_Running_Finish);
		mypetri.getPetrinetelement().add(Arc_Start_Started);
		mypetri.getPetrinetelement().add(Arc_Start_Running);
		mypetri.getPetrinetelement().add(Arc_Finish_Finished);
		
		// Mise à jour des maps
		Ready.put(workDef.getName(), PlaceReady);
	    Started.put(workDef.getName(), PlaceStarted);
	    Running.put(workDef.getName(), PlaceRunning);
	    Finished.put(workDef.getName(), PlaceFinished);
	    Start.put(workDef.getName(), TransitionStart);
	    Finish.put(workDef.getName(), TransitionFinish);
		

	}

}