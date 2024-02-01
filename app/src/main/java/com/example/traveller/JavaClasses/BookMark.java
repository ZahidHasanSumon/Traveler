package com.example.traveller.JavaClasses;

public class BookMark {
    private String PlaceName,PlacesDetails,ImageUrl,ImageUrl1,ImageUrl2,PlaceId;

    public BookMark() {
    }


    public BookMark(String placeName, String placesDetails, String imageUrl, String imageUrl1, String imageUrl2, String placeId) {
        PlaceName = placeName;
        PlacesDetails = placesDetails;
        ImageUrl = imageUrl;
        ImageUrl1 = imageUrl1;
        ImageUrl2 = imageUrl2;
        PlaceId = placeId;
    }


    public String getPlaceName() {
        return PlaceName;
    }

    public void setPlaceName(String placeName) {
        PlaceName = placeName;
    }

    public String getPlacesDetails() {
        return PlacesDetails;
    }

    public void setPlacesDetails(String placesDetails) {
        PlacesDetails = placesDetails;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getImageUrl1() {
        return ImageUrl1;
    }

    public void setImageUrl1(String imageUrl1) {
        ImageUrl1 = imageUrl1;
    }

    public String getImageUrl2() {
        return ImageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        ImageUrl2 = imageUrl2;
    }

    public String getPlaceId() {
        return PlaceId;
    }

    public void setPlaceId(String placeId) {
        PlaceId = placeId;
    }
}
