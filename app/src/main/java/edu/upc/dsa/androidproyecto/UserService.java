package edu.upc.dsa.androidproyecto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @POST("auth/login")
    Call<Jugador> createPostLogin(@Body Jugador jugador);

    @POST("users/registerUser")
    Call<Jugador> createPostRegister(@Body Jugador jugador);

/*
    @POST("tracks/")
    Call<Track> agregarTrack(@Body Track track); */


}
