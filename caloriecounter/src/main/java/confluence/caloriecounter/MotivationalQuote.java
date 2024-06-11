/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package confluential;

/**
 *
 * @author yash
 */
import java.util.Random;

public class MotivationalQuote {

    
    private static final String[] quotes = {
        "You must look within yourself to save yourself from your other self, only then will your true self reveal itself -Prince Zuko",
        "Believe you can and you're halfway there. -Theodore Roosevelt",
        "The only way to do great work is to love what you do. -Steve Jobs",
        "The future belongs to those who believe in the beauty of their dreams. -Eleanor Roosevelt",
        "Success is not final, failure is not fatal: It is the courage to continue that counts. -Winston Churchill",
        "Hardships often prepare ordinary people for an extraordinary destiny. -C.S. Lewis",
        "Your limitation—it's only your imagination.",
        "Push yourself, because no one else is going to do it for you.",
        "Great things never come from comfort zones.",
        "Dream it. Wish it. Do it.",
        "Success doesn’t just find you. You have to go out and get it.",
        "The harder you work for something, the greater you’ll feel when you achieve it.",
        "Dream bigger. Do bigger.",
        "Don’t stop when you’re tired. Stop when you’re done.",
        "Wake up with determination. Go to bed with satisfaction.",
        "Do something today that your future self will thank you for."
    };

    private static final Random random = new Random();

    public static String getRandomQuote() {
        int index = random.nextInt(quotes.length);
        return quotes[index];
    }
}

