import Prelude hiding (sum)

main :: IO()
main = do
    print (sum [1, 2, 3])
    print (sum [2.5, 3.2, 7.7])
    print (sum [5..15])



sum :: (Num a) => [a] -> a
sum [] = 0
sum (x:xs) = x + sum xs
