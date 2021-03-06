package keys;

import encryption.RSA;

import java.math.BigInteger;

abstract class Key
{
    private final BigInteger exponent;

    Key(BigInteger exponent)
    {
        this.exponent = exponent;
    }

    public String getExponentString()
    {
        return this.exponent.toString(RSA.RADIX);
    }
}
