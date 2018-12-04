package ec.cgp.problems;

import ec.*;
import ec.simple.*;
import ec.util.*;
import ec.vector.*;
import ec.cgp.Evaluator;
import ec.cgp.FitnessCGP;
import ec.cgp.representation.VectorIndividualCGP;
import ec.cgp.representation.VectorSpeciesCGP;
import ec.multiobjective.*;

import java.util.*;

/**
 * 
 * Even n-bit parity problem.
 * 
 * @author David Oranchak, doranchak@gmail.com, http://oranchak.com
 *
 */
public class ProblemMultiplier3Bit extends ProblemCGP {
	
	boolean output;

	/** Number of bits in this parity problem. */
	public static int NUM_BITS;
	
	/** Max value represented by this parity problem. */
	public static int max() {
		return (int) Math.pow(2, NUM_BITS);
	}
	
										
	static long[] inputsCompressed1 = new long[]{
			0L, 4294901760L, 4278255360L, 4042322160L, 3435973836L, 2863311530L};
	
	static long[] outputCompressed1 = new long[]{
			0L, 3221225472L, 955252736L, 3033329664L, 1722469376L, 2852170240L};
	
	static long[] inputsCompressed2 = new long[]{
			4294967295L, 4294901760L, 4278255360L, 4042322160L, 3435973836L, 2863311530L};
	
	static long[] outputCompressed2 = new long[]{
			3770712064L, 2553835760L, 1421110476L, 510024362L, 1722469376L, 2852170240L};
												
														
														
	 long getBit(long n, long k) {
		    return (n >> k) & 1;
	 }								

	/** Evaluate the CGP and compute fitness. */
	public void evaluate(EvolutionState state, Individual ind,
			int subpopulation, int threadnum) {
		
		
		if (ind.evaluated)
			return;


		
		VectorSpeciesCGP s = (VectorSpeciesCGP) ind.species;
		VectorIndividualCGP ind2 = (VectorIndividualCGP) ind;
	
	
		NUM_BITS = (int) Math.pow(2, s.numInputs);
		
		int diff = 0;
		Long inputs[] = new Long[s.numInputs];
		
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<s.numInputs; i++) {
			inputs[i] = inputsCompressed1[i];
		}
		
		Object[] outputs = Evaluator.evaluate(state, threadnum, inputs, ind2);

		for (int i=0;i<s.numOutputs;i++)
		{
			long result = (long) outputs[i];
			
			long compare=(long)result^(long)outputCompressed1[i];
	
			for (int j = 0; j < NUM_BITS; j++)
			{
				long temp=compare;
				diff = (int) (diff + getBit(temp,j));
			}

		}
		
		
		for (int i=0; i<s.numInputs; i++) {
			inputs[i] = inputsCompressed2[i];
		}
		
		outputs = Evaluator.evaluate(state, threadnum, inputs, ind2);
		
		
		for (int i=0;i<s.numOutputs;i++)
		{
			long result = (long) outputs[i];
			
			long compare=(long)result^(long)outputCompressed2[i];
			
	
			for (int j = 0; j < NUM_BITS; j++)
			{
				long temp=compare;
				diff = (int) (diff + getBit(temp,j));
			}
			
	
		}
		
		((FitnessCGP)ind.fitness).setFitness(state, diff, diff == 0);
		
		
		if(((FitnessCGP)ind.fitness).isIdealFitness()  && !output)
		{
			System.out.println(state.generation+1);
			output=true;
		}
		
		
		
		ind2.expression.append("  Output: [" + sb + "]");
		ind.evaluated = true;
	}
	
}

