main :: IO()
main = do
    print (divisors 123)
    print (divisors 27)
    print (divisors 158)



divisors :: Integer -> [Integer]
divisors n = [d | d <- [1 .. n - 1], n `mod` d == 0]
