# Dojo Spring
![](https://spring.io/img/spring-by-pivotal-9066b55828deb3c10e27e609af322c40.png)

Le but de cet exercice est de s'entraîner sur un cas simple aux bonnes pratiques de Spring / MVC / Data / Spring Validation mais aussi de pratiquer Java 8 et le développement en TDD.

Il ne concerne que la partie back d'une application. La partie front est censée répondre aux différents contrats d'interface définis dans chacune des étapes de l'énoncé.  

## Consignes
* Tout le code produit doit être testé unitairement.
* Ne regardez les sections Tests unitaires que pour vérifier / compléter vos propres tests.
* Si vous faites ce dojo à plusieurs (fortement conseillé), le faire en mob programming.
* Mettez en place toutes les bonnes pratiques (découpage, règle de nommage, etc.) que vous mettriez en place pour un "vrai" projet.

## Setup

Cloner ce reposiitory et lancer `mvn clean install`.

Lancer la classe `com.bparent.dojo.dojoSpring.DojoSpringApplication` en tant que Spring Boot.

L'application se lance sur : [http://localhost:8080](`http://localhost:8080`). 

Vous pouvez consulter la BDD sur : [http://localhost:8080/console](`http://localhost:8080/console`) (elle est remise à son état initial à chaque démarrage) 

## Step 1.1
Renvoyer la liste de tous les `Todo`

* Méthode : _GET_
* URL : _/todo_
* Paramètres : _Aucun_

### Retour attendu
* Statut : OK (200)
* Body : Une liste de `Todo` avec tous leurs champs.

<details>
<summary>Test unitaire</summary>

### Test unitaire
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
</details>

##  Step 1.2 (facultatif)
Renvoyer la liste de tous les `Todo` dont le texte contient le paramètre

* Méthode : _GET_
* URL : _/todo/search/findByTextContaining?text=P_
* Paramètres : [ _text : string_ 

### Contrainte
Utiliser Spring Data REST uniquement (pas de Controller)

### Retour attendu
* Statut : OK (200)
* Body : Une liste de `Todo` qui contiennent la lettre P.

<details>
<summary>Test unitaire</summary>

### Test unitaire
```java
@Test
public void findByTextContaining_shouldReturn3Records() {
    assertEquals(3, [...].findByTextContaining("P").size());
}
```
</details>

## Step 2.1
Renvoyer la liste de tous les `User`

* Méthode : _GET_
* URL : _/users_
* Paramètres : _Aucun_

### Retour attendu
* Statut : OK (200)
* Body : Une liste de `User`

<details>
<summary>Test unitaire</summary>

### Test unitaire
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
</details>

## Step 2.2
Renvoyer la liste de tous les `User` dont le nom de famille aura été remplacé par le paramètre
* Méthode : _GET_
* URL : _/users/name/{name}_
* Paramètres : [ _name : string_ ]

### Contrainte
Ajouter `@Transactional` sur le controller.

### Retour attendu
* Statut : OK (200)
* Body : Une liste de `User` dont le nom a été remplacé

<details>
<summary>Test unitaire</summary>

### Test unitaire
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
</details>

## Step 2.3
Renvoyer la liste de tous les `User` avec leurs `Todo`

* Méthode : _GET_
* URL : _/users/withTodo_
* Paramètres : _Aucun_

### Retour attendu
* Statut : OK (200)
* Body : Une liste de `User` avec pour chacun, une liste de `Todo` avec tous leurs champs.

<details>
<summary>Test unitaire</summary>

### Test unitaire
```java
@Test
public void findAllWithTodos_shouldReturn2Records() throws Exception {
    [...]

    MvcResult mvcResult = this.mockMvc.perform(get("/users/withTodo"))
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
</details>

<details>
<summary>Aide</summary>

### Aide 
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
</details>

## Step 3
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
### Retour attendu
* Statut OK (200)
* Body : le `Todo` modifié

<details>
<summary>Test unitaire</summary>

### Test unitaire
```java
@Test
public void completeTodo_shouldReturnADto() throws Exception {
    [...]

    MvcResult mvcResult = this.mockMvc.perform(post("/todo/complete")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(("{\"id\":1,\"complete\":true}"))
            )
            .andExpect(status().isOk())
            .andReturn();

    JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
    assertEquals(1, jsonObject.get("id"));
    assertEquals(true, jsonObject.get("complete"));
}
    
===============================================

@Test
public void changeTodoStatus_shouldReturnTdoWithStatusChanged() {
    [...] 
    
    TodoDto todoDto = [...];
    assertEquals(true, todoDto.getComplete());
}

// Facultatif
@Test(expected=NullPointerException.class)
public void changeTodoStatus_shouldThrowAnExceptionIfIdNotFound() {
    [...]

    [... - appel méthode];
    fail("Should have raise an exception");
}
```
</details>

## Step 4.1
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
    
### Retour attendu
* Statut OK (200)
* Body : Le `User` avec tous ses `Todo` (dont le nouveau qui a un id et un status complete à FALSE)

<details>
<summary>Test unitaire</summary>

### Test unitaire
```java
@Test
public void addTodoToUser_shouldReturnAUserDtoReturnedByTheService() throws Exception {
    when(userService.addTodoToUser(3, "New Todo")).thenReturn(UserDto.builder().id(3).build());

    MvcResult mvcResult = this.mockMvc.perform(post("/users/todo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(("{\"id\":3,\"todos\":[{\"text\":\"New Todo\"}]}"))
            )
            .andExpect(status().isOk())
            .andReturn();

    JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());
    assertEquals(3, jsonObject.get("id"));
    
    verify(userService).addTodoToUser(3, "New Todo");
    verifyNoMoreInteractions(userService);
}
===============================================

@Test
public void addTodoToUser_shouldReturnAUserWith3Todos() {
    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
    when(userRepository.findById(3)).thenReturn(Optional.of(
                    User.builder()
                    .todos(new ArrayList<>(Arrays.asList(Todo.builder().build(), Todo.builder().build())))
                    .build()
                    ));

    UserDto userDto = userService.addTodoToUser(3, "New Todo");
    assertEquals(3, userDto.getTodos().size());
    assertEquals("New Todo", userDto.getTodos().get(2).getText());

    // Verify that DB has received the correct object
    verify(userRepository).save(userCaptor.capture());
    User userSavedInDb = userCaptor.getValue();
    assertEquals(3, userSavedInDb.getTodos().size());
    assertEquals("New Todo", userSavedInDb.getTodos().get(2).getText());
}

@Test(expected=NullPointerException.class)
public void changeTodoStatus_shouldThrowAnExceptionIfIdNotFound() {
    when(userRepository.findById(3)).thenReturn(Optional.empty());

    userService.addTodoToUser(3, "New Todo");
    fail("Should have raise an exception");
}
```
</details>


## Step 4.2
Avant d'affecter un `Todo` à un `User`, on vérifie que le texte ne fasse pas plus de 50 caractères

### Retour attendu
* Statut Bad Request (400)

<details>
<summary>Test unitaire</summary>

### Test unitaire
```java
@Test
public void addTodoToUser_shouldReturnAnErrorIfTextIsTooLong() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(post("/users/todo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(("{\"id\":3,\"todos\":[{\"text\":\"Un meessage de plus de 50 caractères devrait faire se déclencher une exception dans le controller\"}]}"))
    )
            .andExpect(status().isBadRequest())
            .andReturn();

    verifyZeroInteractions(userService);
}
```
</details>

## Step 4.3
Avant d'affecter un `Todo` à un `User`, on vérifie que le texte ne contienne pas plus de 2 fois la lettre N et 4 fois la lettre P (majuscule uniquement).

### Retour attendu
* Statut Bad Request (400)

<details>
<summary>Test unitaire</summary>

### Tests unitaires
```java
@Test
public void addTodoToUser_shouldReturnAnErrorIfTooManyN() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(post("/users/todo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(("{\"id\":3,\"todos\":[{\"text\":\"Too many NNN\"}]}"))
    )
            .andExpect(status().isBadRequest())
            .andReturn();

    verifyZeroInteractions(userService);
}
```
</details>
    
<details>
<summary>Aide</summary>

### Aide
```java
org.springframework.util.StringUtils.countOccurrencesOf
```
</details>

## Step 5.1
Supprimer un `Todo` d'un `User`

* Méthode : _DELETE_
* URL : _/users/todo_
* Paramètres : JSON
    ```
    {
        idUser : number,
        idTodo : number 
    }
    ```
### Retour attendu
* Statut OK (200)

<details>
<summary>Test unitaire</summary>

### Tests unitaires
```java
@Test
public void deleteTodoFromUser_shouldCallService() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(delete("/users/todo")
            .contentType(MediaType.APPLICATION_JSON)
            .content(("{\"idUser\":3,\"idTodo\":4}"))
    )
            .andExpect(status().isOk())
            .andReturn();

    verify(userService).deleteTodoFromUser(3, 4);
    verifyNoMoreInteractions(userService);
}

==========================

@Test
public void addTodoToUser_shouldCallDbWithFilteredTodos() {
    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
    when(userRepository.findById(3)).thenReturn(Optional.of(
            User.builder()
                    .todos(new ArrayList<>(Arrays.asList(
                            Todo.builder().id(1).build(),
                            Todo.builder().id(2).build(),
                            Todo.builder().id(3).build())))
                    .build()
    ));

    userService.deleteTodoFromUser(3, 2);

    verify(userRepository).save(userCaptor.capture());
    User userSavedInDb = userCaptor.getValue();
    assertEquals(2, userSavedInDb.getTodos().size());
    assertTrue(userSavedInDb.getTodos().stream().anyMatch(todo -> todo.getId() == 1));
    assertTrue(userSavedInDb.getTodos().stream().anyMatch(todo -> todo.getId() == 3));
}
```
</details>

## Step 5.2
Avant de supprimer un `Todo` d'un `User`, on vérifie qu'il lui appartient.

### Retour attendu
* Statut Forbidden (403)

### Tests unitaires
Partie à revoir pour mocker le validateur

## Licences

![](https://img.shields.io/badge/license-MIT%20License-blue.svg?style=flat-square)

> [basileparent.fr](http://www.basileparent.fr) &nbsp;&middot;&nbsp;
> GitHub [@Casou](https://github.com/Casou) &nbsp;&middot;&nbsp;
> [![Twitter URL](https://img.shields.io/twitter/url/https/twitter.com/fold_left.svg?style=social&label=Follow%20%40basileparent)](https://twitter.com/basileparent)