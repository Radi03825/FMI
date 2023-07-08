import Data.List

main :: IO()
main = do
    print (groupEquals [""] == [[""]])
    print (groupEquals ["a"] == [["a"]])
    print (groupEquals ["eat","tea","tan","ate","nat","bat"] == [["bat"],["ate","eat","tea"],["nat","tan"]])



groupEquals :: [String] -> [[String]]
groupEquals = map (map snd) . groupBy (\ (a, _) (b, _) -> a == b) . sort . map (\ ss -> (sort ss, ss))
