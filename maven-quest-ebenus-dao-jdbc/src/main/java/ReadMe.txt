Reponse question 1 :

Une injection SQL est l'exploitation d'une faille de sécurité d'une application intéragissant avec une base de données.
Cela permet de détourner la requête prévu en y ajoutant une chaine de caractères non prévu par le développeur.




Reponse question 2 :

PreparedStatement comporte plusieurs avantages par rapport à Statement:
PreparedStatement nous aide à prévenir les attaques par injection SQL car il échappe automatiquement aux caractères spéciaux.
PreparedStatement nous permet d'exécuter des requêtes dynamiques avec des entrées de paramètres.
PreparedStatement fournit différents types de méthodes de définition pour définir les paramètres d'entrée de la requête.
PreparedStatement est plus rapide que Statement. Cela devient plus visible lorsque nous réutilisons PreparedStatement ou que nous utilisons ses méthodes de traitement par lots pour exécuter plusieurs requêtes.





Reponse question 3 :

INNER JOIN sélectionne toutes les lignes des deux tables tant qu’il existe une correspondance entre les colonnes.
GAUCHE signifie que toutes les lignes de la table gauche seront retournées, même s’il n’y a pas de ligne correspondante dans la table droite. Cela pourrait entraîner l’affichage de valeurs NULL dans les colonnes renvoyées par la table droite.
RIGHT signifie que toutes les lignes de la table droite seront retournées, même s’il n’y a pas de ligne correspondante dans la table gauche. Cela pourrait entraîner l’affichage de valeurs NULL dans les colonnes renvoyées par la table gauche.
