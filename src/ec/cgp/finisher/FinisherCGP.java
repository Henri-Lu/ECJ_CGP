/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/


package ec.cgp.finisher;

import ec.Finisher;
import ec.EvolutionState;
import ec.Fitness;
import ec.Subpopulation;
import ec.util.Parameter;
import ec.util.QuickSort;

/* 
 * SimpleFinisher.java
 * 
 * Created: Tue Aug 10 21:09:18 1999
 * By: Sean Luke
 */

/**
 * SimpleFinisher is a default Finisher which doesn't do anything.  Most
 * application's don't need Finisher facilities, so this version will work
 * fine.
 *
 * @author Sean Luke
 * @version 1.0 
 */

public class FinisherCGP extends Finisher
{
    public void setup(final EvolutionState state, final Parameter base) { }

    public void finishPopulation(final EvolutionState state, final int result)
    {
    	
    		Subpopulation subpop = state.population.subpops.get(0);	
    		Fitness f;
   
   		
    		double[] fitnessses = new double[subpop.individuals.size()];
    		
    		for(int i=0; i<subpop.individuals.size(); i++ )
    		{
    			f = subpop.individuals.get(i).fitness;
    			fitnessses[i] = f.fitness();
    			
    		}
    		
    		
    		QuickSort.qsort(fitnessses);
    		
    		System.out.println(fitnessses[0]);
 
        return;
        }
    
	
    }

