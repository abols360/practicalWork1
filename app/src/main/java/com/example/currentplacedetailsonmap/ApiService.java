package com.example.currentplacedetailsonmap;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface ApiService {
    @GET("/v3.1/all/") // Define the endpoint
    Call<List<Post>> getPosts(); // Define the request method
}
