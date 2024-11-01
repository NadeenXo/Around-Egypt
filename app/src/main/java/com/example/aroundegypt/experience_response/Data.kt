package com.example.aroundegypt.experience_response

data class Data(
    val address: String,
    val audio_url: String,
    val city: City,
    val cover_photo: String,
    val description: String,
    val detailed_description: String,
    val era: Era,
    val experience_tips: List<Any>,
    val famous_figure: String,
    val founded: String,
    val gmap_location: GmapLocation,
    val has_audio: Boolean,
    val has_video: Int,
    val id: String,
    val is_liked: Any,
    val likes_no: Int,
//    val opening_hours: OpeningHours?, // java.lang.IllegalStateException: Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 4827 path $.data[0].opening_hours
    val period: Period,
    val rating: Int,
    val recommended: Int,
    val reviews: List<Review>,
    val reviews_no: Int,
    val starting_price: Int,
    val tags: List<Tag>,
    val ticket_prices: List<TicketPrice>,
    val title: String,
    val tour_html: String,
    val translated_opening_hours: TranslatedOpeningHours,
    val views_no: Int
)