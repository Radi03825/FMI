main :: IO()
main = do
    print (primesInRange 1 25)
    print (primesInRange' 1 25)



primesInRange :: Int -> Int -> [Int]
primesInRange a b
 | a > b     = []
 | isPrime a = a:primesInRange (a + 1) b
 | otherwise = primesInRange (a + 1) b

isPrime :: Int -> Bool
isPrime n = [d | d <- [1..n], n `mod` d == 0] == [1, n]

primesInRange' :: Int -> Int -> [Int]
primesInRange' a b = [n | n <- [a..b], isPrime n]
