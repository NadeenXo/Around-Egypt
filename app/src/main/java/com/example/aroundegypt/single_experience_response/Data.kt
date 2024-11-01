package com.example.aroundegypt.single_experience_response

import com.google.gson.annotations.SerializedName

data class Data(

    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("cover_photo") var coverPhoto: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("views_no") var viewsNo: Int? = null,
    @SerializedName("likes_no") var likesNo: Int? = null,
    @SerializedName("recommended") var recommended: Int? = null,
    @SerializedName("has_video") var hasVideo: Int? = null,
    @SerializedName("tags") var tags: ArrayList<Tags> = arrayListOf(),
    @SerializedName("city") var city: City? = City(),
    @SerializedName("tours") var tours: ArrayList<String> = arrayListOf(),
    @SerializedName("tour_url") var tourUrl: String? = null,
    @SerializedName("tour_html") var tourHtml: String? = null,
    @SerializedName("tour_xml") var tourXml: String? = null,
    @SerializedName("famous_figure") var famousFigure: String? = null,
    @SerializedName("period") var period: String? = null,
    @SerializedName("era") var era: Era? = Era(),
    @SerializedName("founded") var founded: String? = null,
    @SerializedName("detailed_description") var detailedDescription: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("gmap_location") var gmapLocation: GmapLocation? = GmapLocation(),
//    @SerializedName("opening_hours") var openingHours: OpeningHours? = OpeningHours(),
    @SerializedName("translated_opening_hours") var translatedOpeningHours: TranslatedOpeningHours? = TranslatedOpeningHours(),
    @SerializedName("starting_price") var startingPrice: Int? = null,
    @SerializedName("ticket_prices") var ticketPrices: ArrayList<TicketPrices> = arrayListOf(),
    @SerializedName("experience_tips") var experienceTips: ArrayList<String> = arrayListOf(),
    @SerializedName("reviews") var reviews: ArrayList<String> = arrayListOf(),
    @SerializedName("rating") var rating: Int? = null,
    @SerializedName("reviews_no") var reviewsNo: Int? = null,
    @SerializedName("audio_url") var audioUrl: String? = null,
    @SerializedName("has_audio") var hasAudio: Boolean? = null

)