package edu.upc.dsa.androidproyecto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @POST("auth/login")
    Call<Jugador> createPostLogin(@Body Jugador jugador);

    @POST("users/registerUser")
    Call<Void> createPostRegister(@Body String username, String password, String mail, String name, String lastname, String city );

    @GET("users/getUser/{username}")
    Call<Jugador> getJugadorByUserName(@Path("username")String name);

    @GET("users/getUsers")
    Call<List<Jugador>> getAllusers();

    @PUT("users/updateUser")
    Call<Void> actualizarJugador(@Body Jugador jugador);

    @DELETE("users/deleteUser/{username}")
    Call<Void> eliminarJugador(@Path("username")String username);
/////////////////////////////////////////////////////////////////////////////////////////


    @GET("game/getItems")
    Call<List<Item>> getItems();

    @PUT("game/updateItem")
    Call<Void> comprarItem(@Body Item item);

    @GET("game/getItem/{name}")
    Call<Item> dameItem(@Path("name")String name);





/*
    @POST("tracks/")
    Call<Track> agregarTrack(@Body Track track); */


}
