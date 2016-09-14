package com.webinar.android.hellowebinar.data;

import java.util.Random;

/**
 * Created by aktor on 14/09/16.
 */

public class RandomQuotes {

    private static final String[] QUOTES = {
        "A liberal is a man too broadminded to take his own side in a quarrel.",
            "Winning is not a sometime thing; its an all the time thing. You don't win once in a while; you don't do things right once in a while; you do them right all the time. Winning is a habit.",
            "Politics, n. Strife of interests masquerading as a contest of principles.",
            "I hate television. I hate it as much as I hate peanuts. But I can't stop eating peanuts.",
            "The best time to plant an oak tree was twenty-five years ago. The second best time is today.",
            "America believes in education: the average professor earns more money in a year than a professional athlete earns in a whole week.",
            "If you look good and dress well, you don't need a purpose in life.",
            "To err is human; to forgive, infrequent.",
            "Nihilism is best done by professionals.",
            "I am against using death as a punishment. I am also against using it as a reward.",
            "It is only the great men who are truly obscene. If they had not dared to be obscene, they could never have dared to be great.",
            "I generally avoid temptation unless I can't resist it.",
            "The Internet is like alcohol in some sense. It accentuates what you would do anyway. If you want to be a loner, you can be more alone. If you want to connect, it makes it easier to connect.",
            "An effective way to deal with predators is to taste terrible.",
            "There's no fool like an old fool --- you can't beat experience.",
            "We have a criminal jury system which is superior to any in the world; and its efficiency is only marred by the difficulty of finding twelve men every day who don't know anything and can't read.",
            "I used to think I was poor. Then they told me I wasn't poor, I was needy. Then they told me it was self-defeating to think of myself as needy. I was deprived. (Oh not deprived but rather underprivileged) Then they told me that underprivileged was overused. I was disadvantaged. I still don't have a dime. But I have a great vocabulary.",
            "The most radical revolutionary will become a conservative the day after the revolution.",
            "Writers should be read, but neither seen nor heard.",
            "I'm a born-again atheist."
    };

    private static final Random R = new Random();

    public static String getRandomQuote(){
        int i = R.nextInt(4000);
        return getRandomQuoteIn(i+1000);
    }

    public static String getRandomQuoteIn(long time){
        try {
            Thread.currentThread().sleep(time);
            return getQuote();
        } catch (InterruptedException e) {
            return getQuote();
        }
    }

    private static String getQuote(){
        int i = R.nextInt(QUOTES.length);
        return QUOTES[i];
    }
}
