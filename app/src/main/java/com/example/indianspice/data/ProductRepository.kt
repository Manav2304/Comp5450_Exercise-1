package com.example.indianspice.data

import com.example.indianspice.model.Product

object ProductRepository {

    val products: List<Product> = listOf(
        Product(1, "Kashmiri Saffron", "Premium", 12.99,
            "World's finest saffron hand-picked from the valleys of Kashmir.",
            5.0, 2341, "#D62828", "Kashmir, India",
            listOf("1g", "2g", "5g", "10g"),
            "https://images.unsplash.com/photo-1615485290382-441e4d049cb5?q=80&w=1000&auto=format&fit=crop"),
        Product(2, "Smoked Paprika", "Everyday", 4.49,
            "Slow-smoked over oak wood for a deep, earthy flavour.",
            4.8, 1892, "#F77F00", "Spain",
            imageUrl = "https://images.unsplash.com/photo-1599330103233-03004390979a?q=80&w=1000&auto=format&fit=crop"),
        Product(3, "Black Cardamom", "Whole Spices", 5.99,
            "Bold, smoky cardamom pods from the Himalayan foothills.",
            4.7, 1204, "#2D2D2D", "Nepal",
            imageUrl = "https://images.unsplash.com/photo-1591147551062-875883a45c6f?q=80&w=1000&auto=format&fit=crop"),
        Product(4, "Dried Lavender", "Herbs", 3.99,
            "French culinary lavender with a floral, slightly sweet taste.",
            4.6, 987, "#9B72CF", "Provence, France",
            imageUrl = "https://images.unsplash.com/photo-1595908129746-57ca1a63dd4d?q=80&w=1000&auto=format&fit=crop"),
        Product(5, "Ghost Pepper Flakes", "Hot & Spicy", 6.49,
            "One of the world's hottest peppers, dried and flaked.",
            4.9, 3102, "#FF3D00", "Nagaland, India",
            imageUrl = "https://images.unsplash.com/photo-1588165171080-c89acfa5ee83?q=80&w=1000&auto=format&fit=crop"),
        Product(6, "Ceylon Cinnamon", "Whole Spices", 5.29,
            "True cinnamon from Sri Lanka, lighter and sweeter than cassia.",
            4.8, 2567, "#8B4513", "Sri Lanka",
            imageUrl = "https://images.unsplash.com/photo-1599940824399-b87987ceb72a?q=80&w=1000&auto=format&fit=crop"),
        Product(7, "Dried Rosemary", "Herbs", 2.99,
            "Sun-dried Mediterranean rosemary with pine-like fragrance.",
            4.5, 1456, "#4A7C59", "Italy",
            imageUrl = "https://images.unsplash.com/photo-1589121706692-a16223bc7533?q=80&w=1000&auto=format&fit=crop"),
        Product(8, "Turmeric Powder", "Everyday", 3.49,
            "Vibrant golden turmeric ground from fresh Alleppey roots.",
            4.7, 4210, "#FCBF49", "Kerala, India",
            imageUrl = "https://images.unsplash.com/photo-1615485500704-8e990f9900f7?q=80&w=1000&auto=format&fit=crop"),
        Product(9, "Star Anise", "Whole Spices", 4.79,
            "Beautiful eight-pointed pods with bold, liquorice-like flavour.",
            4.6, 1123, "#6B3A2A", "Vietnam",
            imageUrl = "https://images.unsplash.com/photo-1615485290382-441e4d049cb5?q=80&w=1000&auto=format&fit=crop"),
        Product(10, "Za'atar Blend", "Blends", 5.99,
            "Traditional Middle Eastern herb blend of thyme, sumac and sesame.",
            4.9, 1887, "#7B8C3E", "Lebanon",
            imageUrl = "https://images.unsplash.com/photo-1532139130419-26027a00842b?q=80&w=1000&auto=format&fit=crop"),
        Product(11, "Garam Masala", "Blends", 4.99,
            "House-blended warm spice mix of coriander, cumin and cardamom.",
            4.8, 3340, "#C0622B", "India",
            imageUrl = "https://images.unsplash.com/photo-1599330103233-03004390979a?q=80&w=1000&auto=format&fit=crop"),
        Product(12, "Black Truffle Salt", "Premium", 14.99,
            "Coarse sea salt infused with real Italian black truffle.",
            4.9, 876, "#1A1A1A", "Umbria, Italy",
            listOf("50g", "100g", "200g"),
            "https://images.unsplash.com/photo-1504670073073-6123e39e0754?q=80&w=1000&auto=format&fit=crop")
    )

    fun getById(id: Int): Product? = products.firstOrNull { it.id == id }

    val categories: List<String> =
        listOf("All") + products.map { it.category }.distinct()
}
