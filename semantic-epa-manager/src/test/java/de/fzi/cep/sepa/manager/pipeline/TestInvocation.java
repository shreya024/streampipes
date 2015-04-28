package de.fzi.cep.sepa.manager.pipeline;

import java.util.List;

import de.fzi.cep.sepa.commons.GenericTree;
import de.fzi.cep.sepa.commons.exceptions.NoValidConnectionException;
import de.fzi.cep.sepa.manager.matching.GraphSubmitter;
import de.fzi.cep.sepa.manager.matching.InvocationGraphBuilder;
import de.fzi.cep.sepa.manager.matching.TreeBuilder;
import de.fzi.cep.sepa.messages.PipelineModificationMessage;
import de.fzi.cep.sepa.model.InvocableSEPAElement;
import de.fzi.cep.sepa.model.NamedSEPAElement;
import de.fzi.cep.sepa.model.client.Pipeline;
import de.fzi.cep.sepa.model.client.SEPAElement;
import de.fzi.cep.sepa.model.impl.graph.SEPAInvocationGraph;
import de.fzi.cep.sepa.storage.controller.StorageManager;
import de.fzi.sepa.model.client.util.Utils;

public class TestInvocation {

	public static void main(String[] args)
	{
		List<Pipeline> pipelines = StorageManager.INSTANCE.getPipelineStorageAPI().getAllPipelines();
		Pipeline pipeline = null;
		for(Pipeline p : pipelines)
		{
			if (p.getAction() != null)
			{
				pipeline = p;
				break;
			}
		}
		
		System.out.println(Utils.getGson().toJson(pipeline));
		
		String pipelineId = pipeline.getPipelineId();
		System.out.println(pipelineId);
		StorageManager.INSTANCE.getPipelineStorageAPI().getPipeline(pipelineId);
		
		GenericTree<NamedSEPAElement> tree = new TreeBuilder(pipeline).generateTree(false);
		InvocationGraphBuilder builder = new InvocationGraphBuilder(tree, false);
		List<InvocableSEPAElement> graphs = builder.buildGraph();
		new GraphSubmitter(graphs).invokeGraphs();
		
		/*
		PipelineValidationHandler handler;
		try {
			handler = new PipelineValidationHandler(pipeline, true);
			handler.validateConnection();
			handler.computeMappingProperties();
			PipelineModificationMessage message = handler.getPipelineModificationMessage();
			System.out.println(Utils.getGson().toJson(message));
		} catch (NoValidConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}			
}
