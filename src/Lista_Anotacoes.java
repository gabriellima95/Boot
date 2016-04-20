import java.util.ArrayList;
import java.util.Collections;

//Esta classe Lista_Anotacoes � usada para implementar uma lista de Anotacoes, incluindo todas
//as vari�veis necess�rias.

public class Lista_Anotacoes extends ArrayList<Anotacao> {

	//Construtor da Classe Lista_Anotacoes
	
	public Lista_Anotacoes ()
	{
		super();
	}
	
	//Esta fun��o ordena a lista por data de cria��o/�ltima modifica��o, com a maior data na frente (menor �ndice). 
	
	public void Ordenar_Por_Data ()
	{
		boolean swapped;
		int n = size();
		int i;
		
		do
		{
			swapped = false;
			for (i = 1; i<n; i++)
			{
				if ( get(i-1).getDate().before(get(i).getDate())   )
				{
					Collections.swap(this, i - 1, i);
					swapped = true;
				}
			}
		} while (swapped);
	}
	
	//Esta fun��o ordena a lista por Nome, em ordem alfab�tica, colocando na frente (menor �ndice) os nomes com
	//menor ordem alfab�tica (A-Z: menor - maior �ndice).
	
	public void Ordenar_Por_Nome ()
	{
		boolean swapped;
		int n = size();
		int i;
		
		do
		{
			swapped = false;
			for (i = 1; i<n; i++)
			{
				if ( get(i-1).getNome().compareTo(get(i).getNome()) < 0   )
				{
					Collections.swap(this, i - 1, i);
					swapped = true;
				}
			}
		} while (swapped);
		
	}
	
}