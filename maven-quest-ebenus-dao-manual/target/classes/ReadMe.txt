Reponse question 1 :

- La méthode toString est une méthode définie dans la classe Object, dont toutes les classes Java héritent.
Nativement, elle renvoie le nom de la classe de l'objet concerné suivi de l'adresse de cet objet.
Lorsqu'on la redéfinit, elle permet de donner une description des objets de la classe.

- La méthode equals vérifie si deux objets sont identiques. Nativement, elle vérifie si les deux références pointent vers
la même instance, cependant il est pertinent de redéfinir cette méthode afin de comparer deux objets qui n'ont pas forcément
la même adresse mémoire, mais qui, a nos yeux, peuvent être égaux.

- La méthode hashcode doit fournir un identifiant unique pour chaque objet. Si deux objets sont égaux, ils sont donc censés 
retourner le même hashCode. 



Reponse question 2 :

-List et Tableau: 
	List est une interface, tandis que tableau est un type permettant de stocker plusieurs valeurs, au lieu de les déclarer une par une.


- List et ArrayList:

	List est une interface, contenant des méthodes abstraites, tandis qu'ArrayList est une classe implémentant l'interface List.


- Map et HashMap:
	
	Comme pour les Lists, Map est une interface, tandis que HashMap est une implémentation de cette interface.

- Set et HashSet:

	Set est une interface, contenant donc des méthodes abstraites. HashSet est une implémentation de cette interface.

- List et Set:

	List est un type de collection ordonnée, avec un ordre d'insertion donné, tandis que Set est un type de collection dont les élements ne sont pas ordonnés.

Reponse question 3 :


Le Design Pattern DAO nous permet d’isoler l'application de la base de données de cette dernière, et donc de séparer le stockage du reste de l'application.



