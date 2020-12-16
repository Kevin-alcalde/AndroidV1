package edu.upc.dsa.androidproyecto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("auth/login")
    Call<Jugador> createPostLogin(@Body Jugador jugador);

    @POST("users/registerUser")
    Call<Jugador> createPostRegister(@Body Jugador jugador);

    @GET("users/getUser/{username}")
    Call<Jugador> getJugadorByUserName(@Path("username")String name);








/*
    @POST("tracks/")
    Call<Track> agregarTrack(@Body Track track); */


}
