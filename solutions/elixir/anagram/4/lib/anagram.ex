defmodule Anagram do

  defp normalize(word) do 
      word
      |> String.downcase()
      |> String.replace(~r([^a-z]),"")
      |> String.to_charlist()
      |> Enum.sort()
  end
  @spec match(String.t(), [String.t()]) :: [String.t()]
  @doc """
  Returns all candidates that are anagrams of, but not equal to, 'base'.

  """
  def match(base, candidates) do
    normalized = normalize(base)
    Enum.filter(candidates,&(String.downcase(&1) != String.downcase(base) &&
                             normalize(&1)==normalized))
  end
end