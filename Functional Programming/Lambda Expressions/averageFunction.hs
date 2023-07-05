main :: IO()
main = do
    print ((averageFunction [(+1),(**0.5),(2**)]) 2)



averageFunction :: [(Double -> Double)] -> (Double -> Double)
averageFunction fs = \ x -> average [f x | f <- fs]
  where average xs = sum xs / (fromIntegral (length xs))
