import java.util.ArrayList;
import java.util.Collections;

//Esta classe Lista_Anotacoes é usada para implementar uma lista de Anotacoes, incluindo todas
//as variáveis necessárias.

public class Lista_Anotacoes extends ArrayList<Anotacao> {

	//Construtor da Classe Lista_Anotacoes
	
	public Lista_Anotacoes ()
	{
		super();
	}
	
	//Esta função ordena a lista por data de criação/última modificação, com a maior data na frente (menor índice). 
	
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
	
	//Esta função ordena a lista por Nome, em ordem alfabética, colocando na frente (menor índice) os nomes com
	//menor ordem alfabética (A-Z: menor - maior índice).
	
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