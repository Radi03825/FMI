main :: IO()
main = do
    print (chunksOf 3 [1..10] == [[1,2,3],[4,5,6],[7,8,9],[10]])
    print (chunksOf 5 [7..27] == [[7,8,9,10,11],[12,13,14,15,16],[17,18,19,20,21],[22,23,24,25,26],[27]])



chunksOf :: Int -> [Int] -> [[Int]]
chunksOf n [] = []
chunksOf n xs = take n xs : chunksOf n (drop n xs)
