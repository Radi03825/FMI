main :: IO()
main = do
    print (count [1, 2, 3])
    print (count ['a', 'b', 'c'])
    print (count [5..15])



count :: [a] -> Int
count [] = 0
count (x:xs) = 1 + count xs
-- count xs = 1 + count (tail xs)
