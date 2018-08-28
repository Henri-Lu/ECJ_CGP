package ec.cgp.BA;

import java.util.ArrayList;
import java.util.HashMap;

import ec.BreedingPipeline;
import ec.EvolutionState;
import ec.Individual;
import ec.util.Parameter;
import ec.vector.VectorDefaults;
import ec.vector.VectorIndividual;

public class BAPipeline extends BreedingPipeline{
	

    public static final String P_MUTATION = "mutate";
    public static final int NUM_SOURCES = 1;

	@Override
	public Parameter defaultBase() {
		// TODO Auto-generated method stub
		return VectorDefaults.base().push(P_MUTATION);
	}

	@Override
	public int numSources() {
		// TODO Auto-generated method stub
		return NUM_SOURCES;
	}

	@Override
	public int produce(final int min,
	        final int max,
	        final int subpopulation,
	        final ArrayList<Individual> inds,
	        final EvolutionState state,
	        final int thread, HashMap<String, Object> misc)
	        {
	        int start = inds.size();
	        
	        // grab individuals from our source and stick 'em right into inds.
	        // we'll modify them from there
	        int n = sources[0].produce(min,max,subpopulation,inds, state,thread, misc);

	        // should we use them straight?
	        if (!state.random[thread].nextBoolean(likelihood))
	            {
	            return n;
	            }

	        // else mutate 'em
	        for(int q=start;q<n+start;q++)
	            {
	            ((BAIndividual)inds.get(q)).specialMutate(state,thread);
	            ((BAIndividual)inds.get(q)).evaluated=false;
	            }

	        return n;
	        }

}
