package encryption;/*
* 1. Choose two different prime numbers at random of similar bit-length. They are denoted by p and q.
* 2. Calculate n=p*q. The number n is used as the modules for both private and public keys. Its length
*    is that of the RSA key.
* 3. Calculate a value of Eulers totient function for n: (p-1)*(q-1), denoted as φ(n)
* 4. Choose an integer e that is larger than 1, and smaller than φ(n). The numbers e and φ(n) should be co-prime.
*    The number e is used as the public key exponent.
* 5. Compute a number d such that: d*e = 1 (mod φ(n)). The number d is used as the private key exponent.
*
* NOTES: - Primes might need to be different numbers.
*        - Internally, this method works only with numbers (no text), which are between 0 and n.
*        - It could be the case that the message >= modulus. To avoid, use a do-while loop to generate key
 *         until modulus happen to be exactly N bits.
 *       - It's possible that gcd(phi, publicKey) != 1 in which case the key generation fails. This will only
 *         happen if phi is a multiple of 65537. To avoid, use a do-while loop to generate keys until the gcd is 1.
 *       - All messages should be divided into a number of parts. Then, each part should be converted to a number
 *         (that must be larger than 0 and smaller than n). In practice, the message should be divided into fragments
 *         of the size of a certain number of bits.
 *       - Use the library BigInteger to work with big numbers.
* */



import keys.PrivateKey;
import keys.PublicKey;

public class RSA
{
    public RSA()
    {

    }

    public int decrypt(int c, PrivateKey privateKey)
    {
        int n = privateKey.getModulus();
        int d = privateKey.getExponent();

        return this.fastModExp(c, d, n);
    }

    public int encrypt(int m, PublicKey publicKey)
    {
        int n = publicKey.getModulus();
        int e = publicKey.getExponent();

        return this.fastModExp(m, e, n);
    }

    private int fastModExp(int x, int n, int m)  //x is the base, n is the power, m is the modulus
    {
        int y=1;
        int u=x%m;

        do
        {								//when I use this algorithm in class:
            if(n % 2 == 1)
                y = (y * u) % m;      //this is equivalent to the 1st column
            n = (int)Math.floor(n/2);
            u = (u * u) % m;
        }while(n != 0);

        return y;
    }
}