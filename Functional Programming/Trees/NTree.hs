import Data.List

main :: IO()
main = do
    print (size nTree1)
    print (getLevel nTree1 2)
    print (getLevel nTree2 2)
    print (isGraceful nTree1)
    print (isGraceful nTree2)

    print (mapLevel nTree1 [(*2), (*4), (`div` 100)])
    print (mapLevel nTree1 [show, (nub . show . (* 1000))])
    print (mapLevel nTree2 [(*5), (`div` 4)])
    print (mapLevel nTree3 [(\ _ -> "r"), (\ char -> "w_" ++ [char]), (\ char -> "l_" ++ [char])])



data NTree a = Empty | Node a [NTree a]
    deriving Show

nTree1 :: NTree Int                                 --       1
nTree1 = Node 1 [(Node 2 [(Node 3 [Empty]),         --      / \
                            (Node 4 [Empty]),       --     2   6
                            (Node 5 [Empty])]),     --    /|\  |
                  (Node 6 [(Node 7 [Empty])])]      --   3 4 5 7

nTree2 :: NTree Int                                 --       2
nTree2 = Node 2 [(Node 4 [(Node 6 [Empty]),         --      / \
                            (Node 8 [Empty]),       --     4   12
                            (Node 10 [Empty])]),    --    /|\   |
                  (Node 12 [(Node 14 [Empty])])]    --   6 8 10 14

nTree3 :: NTree Char                                --   's'
nTree3 = Node 's' [Node 's' [                       --    |
                         (Node 's' []),             --   's'
                         (Node 's' [])]]            --   / \
                                                    -- 's' 's'    



size :: NTree a -> Int
size Empty = 0
size (Node _ ts) = 1 + (sum (map size ts))

getLevel :: NTree a -> Int -> [a]
getLevel Empty _ = []
getLevel (Node v _) 0 = [v]
getLevel (Node v ts) l = concatMap (\ tree -> getLevel tree (l - 1)) ts

isGraceful :: NTree Int -> Bool
isGraceful Empty = True
isGraceful (Node _ []) = True
isGraceful (Node _ [Empty]) = True
isGraceful (Node v ts) = all even [abs (v - u) | (Node u _) <- ts] && all isGraceful ts

mapLevel :: NTree a -> [(a -> b)] -> NTree b
mapLevel Empty _ = Empty
mapLevel (Node v []) (f:fs) = Node (f v) []
mapLevel (Node v tr) (f:fs) = if (length fs /= 0) then Node (f v) ([mapLevel x fs | x <- tr]) else Node (f v) []
