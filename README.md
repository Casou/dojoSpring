# Dojo Spring


##Step 1
Renvoyer la liste de tous les `Todo`

* Méthode : _GET_
* URL : _/todos_
* Paramètres : _Aucun_

_**Retour attendu** : Une liste de `Todo` avec tous leurs champs._ 


##Step x
Renvoyer la liste de tous les `User` avec leurs `Todo`

* Méthode : _GET_
* URL : _/users_
* Paramètres : _Aucun_

_**Retour attendu** : Une liste de `User` avec pour chacun, une liste de `Todo` avec tous leurs champs._

##Step x
Renvoyer la liste de tous les `User` sans leurs `Todo`

* Méthode : _GET_
* URL : _/users/clean_
* Paramètres : _Aucun_

_**Retour attendu** : Une liste de `User` avec tous leurs champs sauf les `Todo`_

##Step x
Changer d'état un `Todo` (complete / uncomplete)

* Méthode : _PUT_
* URL : _/todo/complete_
* Paramètres : JSON
    ```
    {
        id : number,
        complete : boolean
    }
    ```
_**Retour attendu** : Statut OK (200)_

##Step x.1
Affecter un `Todo` à un `User` (par défaut uncomplete)

* Méthode : _POST_
* URL : _/users/todo_
* Paramètres : JSON
    ```
    {
        user : {
            id : number
        },
        todo : {
            text : string
        }
    }
    ```
_**Retour attendu** : Statut OK (200)_
    
##Step x.2
Avant d'affecter un `Todo` à un `User`, on vérifie que le texte ne fasse pas plus de 255 caractères

_**Retour attendu** : Statut Bad Request (400)_

##Step x.3
Avant d'affecter un `Todo` à un `User`, on vérifie que le texte ne contienne pas plus de 2 fois la lettre N et quatre fois la lettre P.

_**Retour attendu** : Statut Bad Request (400)_


