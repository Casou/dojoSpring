# Dojo Spring


##Step 1.1
Renvoyer la liste de tous les `Todo`

* Méthode : _GET_
* URL : _/todo_
* Paramètres : _Aucun_

###Retour attendu
* Statut : OK (200)
* Body : Une liste de `Todo` avec tous leurs champs.

###Test unitaire
```java
@Test
public void findAll_shouldReturn2Records() throws Exception {
    [...]

    MvcResult mvcResult = this.mockMvc.perform(get("/todo"))
            .andExpect(status().isOk())
            .andReturn();

    JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
    assertEquals(2, jsonArray.length());

    assertEquals(1, ((JSONObject) jsonArray.get(0)).get("id"));
    assertEquals("Todo 1", ((JSONObject) jsonArray.get(0)).get("text"));
    assertEquals(2, ((JSONObject) jsonArray.get(1)).get("id"));
    assertEquals("Todo 2", ((JSONObject) jsonArray.get(1)).get("text"));
}
```

##Step 1.2 (facultatif)
Renvoyer la liste de tous les `Todo` dont le texte contient le paramètre

* Méthode : _GET_
* URL : _/todo/search/findByTextContaining?text=P_
* Paramètres : [ _text : string_ 

###Contrainte
Utiliser Spring Data REST uniquement (pas de Controller)

###Retour attendu
* Statut : OK (200)
* Body : Une liste de `Todo` qui contiennent la lettre P.

###Test unitaire
```java
@Test
public void findByTextContaining_shouldReturn3Records() {
    assertEquals(3, [...].findByTextContaining("P").size());
}
```

##Step 2.1
Renvoyer la liste de tous les `User`

* Méthode : _GET_
* URL : _/users_
* Paramètres : _Aucun_

###Retour attendu
* Statut : OK (200)
* Body : Une liste de `User`

###Test unitaire
```java
@Test
public void findAll_shouldReturn2Records() throws Exception {
    [...]

    MvcResult mvcResult = this.mockMvc.perform(get("/todo"))
            .andExpect(status().isOk())
            .andReturn();

    JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
    assertEquals(2, jsonArray.length());

    assertEquals(1, ((JSONObject) jsonArray.get(0)).get("id"));
    assertEquals("User 1", ((JSONObject) jsonArray.get(0)).get("login"));
    assertEquals(2, ((JSONObject) jsonArray.get(1)).get("id"));
    assertEquals("User 2", ((JSONObject) jsonArray.get(1)).get("login"));
}
```

##Step 2.2
Renvoyer la liste de tous les `User` dont le nom de famille aura été remplacé par le paramètre
* Méthode : _GET_
* URL : _/users/name/{name}_
* Paramètres : [ _name : string_ ]

###Contrainte
Ajouter `@Transactional` sur le controller.

###Retour attendu
* Statut : OK (200)
* Body : Une liste de `User` dont le nom a été remplacé

###Test unitaire
```java
@Test
public void findAllAndReplaceName_shouldReturn2RecordsWithASpecificName() throws Exception {
    [...]
    
    MvcResult mvcResult = this.mockMvc.perform(get("/users/name/toto"))
            .andExpect(status().isOk())
            .andReturn();

    JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
    assertEquals(2, jsonArray.length());

    assertEquals(1, ((JSONObject) jsonArray.get(0)).get("id"));
    assertEquals("toto", ((JSONObject) jsonArray.get(0)).get("name"));
    assertEquals(2, ((JSONObject) jsonArray.get(1)).get("id"));
    assertEquals("toto", ((JSONObject) jsonArray.get(1)).get("name"));
}
```

##Step 2.3
Renvoyer la liste de tous les `User` avec leurs `Todo`

* Méthode : _GET_
* URL : _/users/complete_
* Paramètres : _Aucun_

###Retour attendu
* Statut : OK (200)
* Body : Une liste de `User` avec pour chacun, une liste de `Todo` avec tous leurs champs.

###Test unitaire
```java
@Test
public void findAllWithTodos_shouldReturn2Records() throws Exception {
    [...]

    MvcResult mvcResult = this.mockMvc.perform(get("/users/complete"))
            .andExpect(status().isOk())
            .andReturn();

    JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());
    assertEquals(2, jsonArray.length());

    assertEquals(1, ((JSONObject) jsonArray.get(0)).get("id"));
    assertEquals("User 1", ((JSONObject) jsonArray.get(0)).get("login"));
    assertEquals(2, ((JSONArray) ((JSONObject) jsonArray.get(0)).get("todos")).length());
    assertEquals(1, ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(0)).get("todos")).get(0)).get("id"));
    assertEquals("Todo 1", ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(0)).get("todos")).get(0)).get("text"));
    assertEquals(2, ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(0)).get("todos")).get(1)).get("id"));
    assertEquals("Todo 2", ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(0)).get("todos")).get(1)).get("text"));

    assertEquals(2, ((JSONObject) jsonArray.get(1)).get("id"));
    assertEquals("User 2", ((JSONObject) jsonArray.get(1)).get("login"));
    assertEquals(1, ((JSONArray) ((JSONObject) jsonArray.get(1)).get("todos")).length());
    assertEquals(3, ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(1)).get("todos")).get(0)).get("id"));
    assertEquals("Todo 3", ((JSONObject) ((JSONArray) ((JSONObject) jsonArray.get(1)).get("todos")).get(0)).get("text"));
}

```

###Aide 
```java
import org.modelmapper.ModelMapper;

public abstract class AbstractDto<E> {

    public AbstractDto toDto(E entity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(entity, this.getClass());
    }

}

===============================================

public class UserDto extends AbstractDto<User> {

    [...]

    @Override
    public UserDto toDto(User entity) {
        return (UserDto) super.toDto(entity);
    }

}
```


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
###Retour attendu
* Statut OK (200)
* Body : le `Todo` modifié


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
###Retour attendu
* Statut OK (200)
* Body : Todo avec un id


##Step x.2
Avant d'affecter un `Todo` à un `User`, on vérifie que le texte ne fasse pas plus de 255 caractères

###Retour attendu
* Statut Bad Request (400)
* Body : un message d'erreur explicite


##Step x.3
Avant d'affecter un `Todo` à un `User`, on vérifie que le texte ne contienne pas plus de 2 fois la lettre N et quatre fois la lettre P.

###Retour attendu
* Statut Bad Request (400)
* Body : un message d'erreur explicite


##Step x.1
Supprimer un `Todo` d'un `User`

* Méthode : _DELETE_
* URL : _/users/todo_
* Paramètres : JSON
    ```
    {
        user : {
            id : number
        },
        todo : {
            id : number
        }
    }
    ```
###Retour attendu
* Statut OK (200)


##Step x.2
Avant de supprimer un `Todo` d'un `User`, on vérifie qu'il lui appartient.

###Retour attendu
* Statut Bad Request (400)
* Body : un message d'erreur explicite
