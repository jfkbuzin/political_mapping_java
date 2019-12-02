package mapeamentopolitico;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Heapsort{
	public ArrayList<Estrutura> heapSort(ArrayList<Estrutura> unsortedList)
	{
                Object[] possibleValues2 = { "Ordem Decrescente de Frequencia", "Ordem Crescente de Frequencia"};
                Object selectedValue2 = JOptionPane.showInputDialog(null,"Escolha um", "Input"
                    ,JOptionPane.INFORMATION_MESSAGE, null,possibleValues2, possibleValues2[0]); 
                                    
                if(selectedValue2 == "Ordem Crescente de Frequencia")
                   JOptionPane.showMessageDialog(null, "ordenando em ordem crescente de frequencia");
                else
                   JOptionPane.showMessageDialog(null, "ordenando em ordem decrescente de frequencia");
                
                System.out.println("lista desordenada");
                for(Estrutura com : unsortedList){
                    System.out.print(com.getFreq()+ " ");
                }
                System.out.println("");
                
                
                
                int count = unsortedList.size();
                System.out.println("count:" + count);
		heapify(unsortedList, count, selectedValue2);
                
                System.out.println("depois do primeiro heapify");
                for(Estrutura com : unsortedList){
                    System.out.print(com.getFreq()+ " ");
                }
                System.out.println("");
                
		int end = count-1;
		while(end > 0) //troca raiz com último até o array ficar na ordem correta
		{
			System.out.println("end:" + end);
                        swap(unsortedList, end, 0);
                        
                        System.out.println("troca raiz com ultimo : siftdown swapped com end" + end);
                for(Estrutura com : unsortedList){
                    System.out.print(com.getFreq()+ " ");
                }
                System.out.println("");                   
                        
			end = end - 1;
			if(selectedValue2 == "Ordem Crescente de Frequencia")
                            siftDown2(unsortedList, 0, end);
                        else
                            siftDown(unsortedList, 0, end);
                        
                        System.out.println("siftdown com end" + end);
                for(Estrutura com : unsortedList){
                    System.out.print(com.getFreq()+ " ");
                }
                System.out.println("");
		}
		return unsortedList;
	}
	public void heapify(ArrayList<Estrutura> unsortedList, int count, Object selectedValue2)
	{
		int start = count/2 - 1;
		while(start >= 0)
		{
			 System.out.println("start:" + start);
                    
                        if(selectedValue2 == "Ordem Crescente de Frequencia")
                            siftDown2(unsortedList, start, count - 1);
                        else
                            siftDown(unsortedList, start, count - 1);
			
                        System.out.println("siftdown com start" + start);
                for(Estrutura com : unsortedList){
                    System.out.print(com.getFreq()+ " ");
                }
                System.out.println("");
                        
                        start -= 1;
		}
	}
	public void siftDown(ArrayList<Estrutura> unsortedList, int start, int end)
	{
		int root = start;
		while(root*2+1 <= end)
		{
			int child = root*2+1;
                         System.out.println("child:" + child);
			int swap = root;
                        System.out.println("swap:" + swap);
                        
			if(unsortedList.get(swap).getFreq() > unsortedList.get(child).getFreq()) //aqui fica max ou min heap
			{
				swap = child;
			}
			if(child+1 <= end && unsortedList.get(swap).getFreq() > unsortedList.get(child+1).getFreq()) //aqui fica max ou min heap
			{
				swap = child+1;
			}
			if(swap != root)
			{
				swap(unsortedList, root, swap);
                                
                                //System.out.println("siftdown swapped com swap" + swap + "e root" + root);
                //for(Estrutura com : unsortedList){
                 //   System.out.print(com.getFreq()+ " ");
                //}
                //System.out.println("");
                                
				root = swap;
                                
                                
                                //System.out.println("siftdown swapped com swap" + swap + "e root" + root + "trocados");
                //for(Estrutura com : unsortedList){
                //    System.out.print(com.getFreq()+ " ");
                //}
                //System.out.println("");                                
			}
			else
			{
				return;
			}
		}
	}
	public void siftDown2(ArrayList<Estrutura> unsortedList, int start, int end)
	{
		int root = start;
		while(root*2+1 <= end)
		{
			int child = root*2+1;
			int swap = root;

			if(unsortedList.get(swap).getFreq() < unsortedList.get(child).getFreq()) //aqui fica max ou min heap
			{
				swap = child;
			}
			if(child+1 <= end && unsortedList.get(swap).getFreq() < unsortedList.get(child+1).getFreq()) //aqui fica max ou min heap
			{
				swap = child+1;
			}
			if(swap != root)
			{
				swap(unsortedList, root, swap);
				root = swap;
			}
			else
			{
				return;
			}
		}
	}        
        
	public void swap(ArrayList<Estrutura> unsortedList, int swapOne, int swapTwo)
	{
		Estrutura holder = unsortedList.get(swapOne);
		unsortedList.set(swapOne, unsortedList.get(swapTwo));
		unsortedList.set(swapTwo, holder);
	}
	
}

